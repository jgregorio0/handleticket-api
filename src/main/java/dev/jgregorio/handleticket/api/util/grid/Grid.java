package dev.jgregorio.handleticket.api.util.grid;

import com.google.cloud.vision.v1.EntityAnnotation;
import dev.jgregorio.handleticket.api.model.NormalizedText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Grid {

    int yErrorMax;
    String rowSeparator, colSeparator;
    List<Row> rows;

    public Grid(int yErrorMax, String rowSeparator, String colSeparator) {
        this.yErrorMax = yErrorMax;
        this.rowSeparator = rowSeparator;
        this.colSeparator = colSeparator;
        this.rows = new ArrayList<>();
    }

    public void addAnnotations(List<EntityAnnotation> annotations) {
        annotations.stream()
                .filter(annotation -> !annotation.getDescription().contains("\n"))
                .map(entityAnnotation -> new NormalizedText(entityAnnotation))
                .collect(Collectors.toList())
                .forEach(normalizedText -> add2Row(normalizedText));
        rows.stream().forEach(row -> Collections.sort(row.getTexts(), new RowComparator()));
    }

    public String getTextFormatted() {
        StringBuffer sb = new StringBuffer();
        for (Row row : rows) {
            sb.append(rowSeparator);
            for (int i = 0; i < row.getTexts().size(); i++) {
                NormalizedText normalizedText = row.getTexts().get(i);
                String text = normalizedText.getText();
                String toAppend = i == 0 ? text : colSeparator + text;
                sb.append(toAppend);
            }
        }
        return sb.toString();
    }

    private void add2Row(NormalizedText normalizedText) {
        Row assignedRow = assignRow(normalizedText.getY());
        assignedRow.getTexts().add(normalizedText);
    }

    private Row assignRow(int y) {
        Row assignedRow = null;
        Optional<Row> assignedRowOption = rows.stream()
                .filter(row -> row.isInRange(y))
                .findFirst();
        if (assignedRowOption.isPresent()) {
            assignedRow = assignedRowOption.get();
        } else {
            assignedRow = new Row(y, getyErrorMax());
            getRows().add(assignedRow);
        }
        return assignedRow;
    }

    public List<Row> getRows() {
        return rows;
    }

    public int getyErrorMax() {
        return yErrorMax;
    }
}
