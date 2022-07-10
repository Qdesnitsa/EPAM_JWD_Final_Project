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

    private CustomerDto() {}

    public CustomerDto(String name, String surname, String email, UserStatus status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) &&
                Objects.equals(email, that.email) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                "; ";
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
