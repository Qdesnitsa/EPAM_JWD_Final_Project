package by.sidina.it_team.entity;

import java.io.Serializable;
import java.util.Objects;

public class TeamPositionLevel implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int employeeID;
    private int employeePositionID;
    private int employeeLevelID;

    private TeamPositionLevel() {
    }

    public TeamPositionLevel(int employeeID, int employeePositionID, int employeeLevelID) {
        this.employeeID = employeeID;
        this.employeePositionID = employeePositionID;
        this.employeeLevelID = employeeLevelID;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeePositionID() {
        return employeePositionID;
    }

    public void setEmployeePositionID(int employeePositionID) {
        this.employeePositionID = employeePositionID;
    }

    public int getEmployeeLevelID() {
        return employeeLevelID;
    }

    public void setEmployeeLevelID(int employeeLevelID) {
        this.employeeLevelID = employeeLevelID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamPositionLevel that = (TeamPositionLevel) o;
        return id == that.id && employeeID == that.employeeID && employeePositionID == that.employeePositionID &&
                employeeLevelID == that.employeeLevelID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeID, employeePositionID, employeeLevelID);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", employeeID=" + employeeID +
                ", employeePositionID=" + employeePositionID +
                ", employeeLevelID=" + employeeLevelID +
                "; ";
    }
}
