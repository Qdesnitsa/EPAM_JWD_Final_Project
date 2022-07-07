package by.sidina.it_team.dao.dto;

import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.UserStatus;

public class EmployeeDto {
    private int id;
    private String name;
    private String surname;
    private Role role;
    private String email;
    private UserStatus status;
    private String position;
    private Level level;
    private double rate;

    private EmployeeDto() {}

    public EmployeeDto(String name, String surname, Role role, String email, UserStatus status, String position,
                       Level level, double rate) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.status = status;
        this.position = position;
        this.level = level;
        this.rate = rate;
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

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }

    public Level getLevel() {
        return level;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", position='" + position + '\'' +
                ", level=" + level +
                ", rate=" + rate +
                "; ";
    }

    public static class Builder {
        private final EmployeeDto employeeDto;

        public Builder() {
            employeeDto = new EmployeeDto();
        }

        public EmployeeDto.Builder setId(int id) {
            this.employeeDto.id = id;
            return this;
        }

        public EmployeeDto.Builder setName(String name) {
            this.employeeDto.name = name;
            return this;
        }

        public EmployeeDto.Builder setSurname(String surname) {
            this.employeeDto.surname = surname;
            return this;
        }

        public EmployeeDto.Builder setRole(Role role) {
            this.employeeDto.role = role;
            return this;
        }

        public EmployeeDto.Builder setEmail(String email) {
            this.employeeDto.email = email;
            return this;
        }

        public EmployeeDto.Builder setStatus(UserStatus status) {
            this.employeeDto.status = status;
            return this;
        }

        public EmployeeDto.Builder setPosition(String position) {
            this.employeeDto.position = position;
            return this;
        }

        public EmployeeDto.Builder setLevel(Level level) {
            this.employeeDto.level = level;
            return this;
        }

        public EmployeeDto.Builder setRate(double rate) {
            this.employeeDto.rate = rate;
            return this;
        }

        public EmployeeDto build() {
            return employeeDto;
        }
    }
}
