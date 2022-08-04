package by.sidina.it_team.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private int projectStatusID;
    private String requirementComment;

    private int customerID;

    private Project() {
    }

    public Project(String name, Date startDate, Date endDate, int projectStatusID, String requirementComment, int customerID) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectStatusID = projectStatusID;
        this.requirementComment = requirementComment;
        this.customerID = customerID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getProjectStatusID() {
        return projectStatusID;
    }

    public String getRequirementComment() {
        return requirementComment;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && projectStatusID == project.projectStatusID && customerID == project.customerID &&
                Objects.equals(name, project.name) && Objects.equals(startDate, project.startDate) &&
                Objects.equals(endDate, project.endDate) && Objects.equals(requirementComment, project.requirementComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, projectStatusID, requirementComment, customerID);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectStatusID=" + projectStatusID +
                ", requirementComment='" + requirementComment + '\'' +
                ", customerID=" + customerID +
                "; ";
    }

    public static class Builder {
        private final Project newProject;

        public Builder() {
            newProject = new Project();
        }

        public Project.Builder setId(int id) {
            newProject.id = id;
            return this;
        }

        public Project.Builder setName(String name) {
            newProject.name = name;
            return this;
        }

        public Project.Builder setStartDate(Date startDate) {
            newProject.startDate = startDate;
            return this;
        }

        public Project.Builder setEndDate(Date endDate) {
            newProject.endDate = endDate;
            return this;
        }

        public Project.Builder setProjectStatusID(int projectStatusID) {
            newProject.projectStatusID = projectStatusID;
            return this;
        }

        public Project.Builder setRequirementComment(String requirementComment) {
            newProject.requirementComment = requirementComment;
            return this;
        }

        public Project.Builder setCustomerID(int id) {
            newProject.customerID = id;
            return this;
        }

        public Project build() {
            return newProject;
        }

    }
}
