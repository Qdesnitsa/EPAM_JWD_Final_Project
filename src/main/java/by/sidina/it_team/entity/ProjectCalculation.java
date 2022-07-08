package by.sidina.it_team.entity;

import java.util.Objects;

public class ProjectCalculation {
    private int id;
    private int projectID;
    private double hoursPlan;
    private double costPlan;

    private ProjectCalculation() {
    }

    public ProjectCalculation(int projectID, double hoursPlan, double costPlan) {
        this.projectID = projectID;
        this.hoursPlan = hoursPlan;
        this.costPlan = costPlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public double getHoursPlan() {
        return hoursPlan;
    }

    public void setHoursPlan(double hoursPlan) {
        this.hoursPlan = hoursPlan;
    }

    public double getCostPlan() {
        return costPlan;
    }

    public void setCostPlan(double costPlan) {
        this.costPlan = costPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectCalculation that = (ProjectCalculation) o;
        return id == that.id && projectID == that.projectID && Double.compare(that.hoursPlan, hoursPlan) == 0 &&
                Double.compare(that.costPlan, costPlan) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectID, hoursPlan, costPlan);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", projectID=" + projectID +
                ", hoursPlan=" + hoursPlan +
                ", costPlan=" + costPlan +
                "; ";
    }
}
