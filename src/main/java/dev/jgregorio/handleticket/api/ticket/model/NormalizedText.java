package dev.jgregorio.handleticket.api.ticket.model;

import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Vertex;

public class NormalizedText implements Comparable<NormalizedText> {
    String text;
    int x;
    int y;

    public NormalizedText() {
    }

    public NormalizedText(EntityAnnotation annotation) {
        this.text = annotation.getDescription();
        this.x = annotation.getBoundingPoly().getVertices(Vertex.X_FIELD_NUMBER).getX();
        this.y = annotation.getBoundingPoly().getVertices(Vertex.Y_FIELD_NUMBER).getY();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(NormalizedText o) {
        if (getY() < o.getY()) {
            return -1;
        } else if (getY() == o.getY()) {
            if (getX() < o.getX()) {
                return -1;
            } else if (getX() == o.getX()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "NormalizedText{" +
                "text='" + text + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
