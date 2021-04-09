package dev.jgregorio.handleticket.api.ticket.model.grid;

import dev.jgregorio.handleticket.api.ticket.model.NormalizedText;

import java.util.Comparator;

public class RowComparator implements Comparator<NormalizedText> {

    @Override
    public int compare(NormalizedText o1, NormalizedText o2) {
        return Integer.compare(o1.getX(), o2.getX());
    }
}
