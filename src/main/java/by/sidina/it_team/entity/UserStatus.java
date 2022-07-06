package by.sidina.it_team.entity;

public enum UserStatus {
    ACTIVE(1), BLOCKED(2);

    private final int userStatusID;
    UserStatus(int id) {
        this.userStatusID = id;
    }

    public int getUserStatusID() {
        return userStatusID;
    }
}
