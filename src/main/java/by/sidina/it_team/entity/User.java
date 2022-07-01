package by.sidina.it_team.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String surname;
    private  int role_id;
    private String email;
    private int status_id;

    private User() {}

    public User(String name, String surname, int role_id, String email, int status_id) {
        this.name = name;
        this.surname = surname;
        this.role_id = role_id;
        this.email = email;
        this.status_id = status_id;
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

    public int getRole_id() {
        return role_id;
    }

    public String getEmail() {
        return email;
    }

    public int getStatus_id() {
        return status_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && role_id == user.role_id && status_id == user.status_id &&
                Objects.equals(name, user.name) && Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, role_id, email, status_id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", name='" + name +
                ", surname='" + surname +
                ", role_id=" + role_id +
                ", email='" + email +
                ", status_id=" + status_id + "; ";
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

        public Builder setRole_id(int role_id) {
            newUser.role_id = role_id;
            return this;
        }

        public Builder setEmail(String email) {
            newUser.email = email;
            return this;
        }

        public Builder setStatus_id(int status_id) {
            newUser.status_id = status_id;
            return this;
        }

        public User build() {
            return newUser;
        }

    }
}
