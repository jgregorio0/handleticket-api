package dev.jgregorio.handleticket.api.ticket.model.grid;

import dev.jgregorio.handleticket.api.ticket.model.NormalizedText;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private int yCreatedPosition, min, max;
    private List<NormalizedText> texts;

    public Row(int yPosition, int yErrorMax) {
        this.yCreatedPosition = yPosition;
        this.min = yPosition - yErrorMax;
        this.max = yPosition + yErrorMax;
        this.texts = new ArrayList<>();
    }

    public int getyCreatedPosition() {
        return yCreatedPosition;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean isInRange(int y) {
        return getMin() <= y && y < getMax();
    }

    public List<NormalizedText> getTexts() {
        return texts;
    }
}
