package by.sidina.it_team.entity;

public enum Role {
    ADMIN(1), EMPLOYEE(2), CUSTOMER(3);

    private final int roleID;

    Role(int roleId) {
        this.roleID = roleId;
    }

    public int getId() {
        return roleID;
    }
}
