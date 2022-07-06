package by.sidina.it_team.entity;

public enum ProjectStatus {
    NEW(1), PREPARED(2), ACTIVE(3), CANCELLED(4), FINISHED(5);

    private final int projectStatusID;

    ProjectStatus(int id) {
        this.projectStatusID = id;
    }

    public int getProjectStatusID() {
        return projectStatusID;
    }
}
