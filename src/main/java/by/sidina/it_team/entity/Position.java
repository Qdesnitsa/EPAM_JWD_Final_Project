package by.sidina.it_team.entity;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String position;

    private Position() {}

    public Position(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return id == position1.id && Objects.equals(position, position1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", position='" + position +
                "; ";
    }
}
