package by.sidina.it_team.dao.dto;

import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.UserStatus;

import java.util.Objects;

public class CustomerDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private UserStatus status;
    private int statusId;

    private CustomerDto() {
    }

    public CustomerDto(String name, String surname, String email, UserStatus status, int statusId) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public int getStatusId() {
        return statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return id == that.id && statusId == that.statusId && Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, status, statusId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", statusId=" + statusId +
                " ;";
    }

    public static class Builder {
        private final CustomerDto customerDto;

        public Builder() {
            customerDto = new CustomerDto();
        }

        public CustomerDto.Builder setId(int id) {
            this.customerDto.id = id;
            return this;
        }

        public CustomerDto.Builder setStatusId(int statusId) {
            this.customerDto.statusId = statusId;
            return this;
        }

        public CustomerDto.Builder setName(String name) {
            this.customerDto.name = name;
            return this;
        }

        public CustomerDto.Builder setSurname(String surname) {
            this.customerDto.surname = surname;
            return this;
        }

        public CustomerDto.Builder setEmail(String email) {
            this.customerDto.email = email;
            return this;
        }

        public CustomerDto.Builder setStatus(UserStatus status) {
            this.customerDto.status = status;
            return this;
        }

        public CustomerDto build() {
            return customerDto;
        }
    }
}
