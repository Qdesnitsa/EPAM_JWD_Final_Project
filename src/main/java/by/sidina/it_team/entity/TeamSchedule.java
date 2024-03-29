package by.sidina.it_team.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class TeamSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int employee_id;
    private int project_id;
    private Date date;
    private double hours_fact;

    private TeamSchedule() {
    }

    public TeamSchedule(int employee_id, int project_id, Date date, double hours_fact) {
        this.employee_id = employee_id;
        this.project_id = project_id;
        this.date = date;
        this.hours_fact = hours_fact;
    }

    public int getId() {
        return id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getHours_fact() {
        return hours_fact;
    }

    public void setHours_fact(double hours_fact) {
        this.hours_fact = hours_fact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamSchedule that = (TeamSchedule) o;
        return id == that.id && employee_id == that.employee_id && project_id == that.project_id &&
                Double.compare(that.hours_fact, hours_fact) == 0 &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee_id, project_id, date, hours_fact);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", employee_id=" + employee_id +
                ", project_id=" + project_id +
                ", date=" + date +
                ", hours_fact=" + hours_fact + "; ";
    }
}
