package by.sidina.it_team.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String surname;
    private int roleId;
    private String email;
    private int statusId;

    private User() {
    }

    public User(String name, String surname, int roleId, String email, int statusId) {
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
        this.email = email;
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getEmail() {
        return email;
    }

    public int getStatusId() {
        return statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && statusId == user.statusId &&
                Objects.equals(name, user.name) && Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, roleId, email, statusId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", name='" + name +
                ", surname='" + surname +
                ", role_id=" + roleId +
                ", email='" + email +
                ", status_id=" + statusId + "; ";
    }

    public static class Builder {
        private final User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder setId(int id) {
            newUser.id = id;
            return this;
        }

        public Builder setName(String name) {
            newUser.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            newUser.surname = surname;
            return this;
        }

        public Builder setRole_id(int roleId) {
            newUser.roleId = roleId;
            return this;
        }

        public Builder setEmail(String email) {
            newUser.email = email;
            return this;
        }

        public Builder setStatus_id(int statusId) {
            newUser.statusId = statusId;
            return this;
        }

        public User build() {
            return newUser;
        }

    }
}
