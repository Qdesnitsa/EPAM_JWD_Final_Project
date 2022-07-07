package by.sidina.it_team.dao.dto;

import by.sidina.it_team.entity.ProjectStatus;
import by.sidina.it_team.entity.UserStatus;

import java.sql.Date;
import java.util.Objects;

public class ProjectDto {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private ProjectStatus status;
    private int customerId;
    private double amount;
    private double hoursFact;
    private double hoursPlan;
    private double costPlan;

    private String requirementComment;

    private ProjectDto() {

    }

    public ProjectDto(int id, String name, Date startDate, Date endDate, ProjectStatus status, int customerId,
                      double amount, double hoursFact, double hoursPlan, double costPlan) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.customerId = customerId;
        this.amount = amount;
        this.hoursFact = hoursFact;
        this.hoursPlan = hoursPlan;
        this.costPlan = costPlan;
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

    public ProjectStatus getStatus() {
        return status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public double getHoursFact() {
        return hoursFact;
    }

    public double getHoursPlan() {
        return hoursPlan;
    }

    public double getCostPlan() {
        return costPlan;
    }

    public String getRequirementComment() {
        return requirementComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDto projectDto = (ProjectDto) o;
        return id == projectDto.id && customerId == projectDto.customerId &&
                Double.compare(projectDto.amount, amount) == 0 &&
                Double.compare(projectDto.hoursFact, hoursFact) == 0 &&
                Double.compare(projectDto.hoursPlan, hoursPlan) == 0 &&
                Double.compare(projectDto.costPlan, costPlan) == 0 &&
                Objects.equals(name, projectDto.name) &&
                Objects.equals(startDate, projectDto.startDate) &&
                Objects.equals(endDate, projectDto.endDate) &&
                Objects.equals(status, projectDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, status, customerId, amount, hoursFact, hoursPlan, costPlan);
    }

    public static class Builder {
        private final ProjectDto projectDto;

        public Builder() {
            projectDto = new ProjectDto();
        }

        public Builder setId(int id) {
            this.projectDto.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.projectDto.name = name;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            this.projectDto.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Date endDate) {
            this.projectDto.endDate = endDate;
            return this;
        }

        public Builder setStatus(ProjectStatus status) {
            this.projectDto.status = status;
            return this;
        }

        public Builder setCustomerId(int customerId) {
            this.projectDto.customerId = customerId;
            return this;
        }

        public Builder setAmount(double amount) {
            this.projectDto.amount = amount;
            return this;
        }

        public Builder setHoursFact(double hoursFact) {
            this.projectDto.hoursFact = hoursFact;
            return this;
        }

        public Builder setHoursPlan(double hoursPlan) {
            this.projectDto.hoursPlan = hoursPlan;
            return this;
        }

        public Builder setCostPlan(double costPlan) {
            this.projectDto.costPlan = costPlan;
            return this;
        }

        public Builder setRequirementComment(String requirementComment) {
            this.projectDto.requirementComment = requirementComment;
            return this;
        }

        public ProjectDto build() {
            return projectDto;
        }
    }
}
