package by.sidina.it_team.entity;

import java.io.Serializable;
import java.util.Objects;

public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int positionID;
    private int levelID;
    private double rate;

    private Rate() {
    }

    public Rate(int positionID, int levelID, double rate) {
        this.positionID = positionID;
        this.levelID = levelID;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate1 = (Rate) o;
        return id == rate1.id && positionID == rate1.positionID && levelID == rate1.levelID &&
                Double.compare(rate1.rate, rate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionID, levelID, rate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", positionID=" + positionID +
                ", levelID=" + levelID +
                ", rate=" + rate +
                "; ";
    }
}
