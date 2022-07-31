package by.sidina.it_team.dao.dto;

import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.UserStatus;

import java.util.Objects;

public class EmployeeDto {
    private int id;
    private String name;
    private String surname;
    private Role role;
    private String email;
    private UserStatus status;
    private int statusId;
    private String position;
    private int positionId;
    private Level level;
    private int levelId;
    private double rate;
    private double hours;

    private EmployeeDto() {}

    public EmployeeDto(String name, String surname, Role role, String email, UserStatus status, String position,
                       Level level, double rate, double hours, int positionId, int levelId, int statusId) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.status = status;
        this.position = position;
        this.level = level;
        this.rate = rate;
        this.hours = hours;
        this.positionId = positionId;
        this.levelId = levelId;
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

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public UserStatus getStatus() {
        return status;
    }
    public int getStatusId() { return statusId; }

    public String getPosition() {
        return position;
    }
    public int getPositionId() {
        return positionId;
    }

    public Level getLevel() {
        return level;
    }
    public int getLevelId() {
        return levelId;
    }

    public double getHours() {
        return hours;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return id == that.id && statusId == that.statusId && positionId == that.positionId &&
                levelId == that.levelId && Double.compare(that.rate, rate) == 0 &&
                Double.compare(that.hours, hours) == 0 && Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) && role == that.role && Objects.equals(email, that.email) &&
                status == that.status && Objects.equals(position, that.position) && level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, role, email, status, statusId, position, positionId, level, levelId, rate, hours);
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
                ", statusId=" + statusId +
                ", position='" + position + '\'' +
                ", positionId=" + positionId +
                ", level=" + level +
                ", levelId=" + levelId +
                ", rate=" + rate +
                ", hours=" + hours +
                " ;";
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

        public EmployeeDto.Builder setPositionId(int positionId) {
            this.employeeDto.positionId = positionId;
            return this;
        }

        public EmployeeDto.Builder setLevelId(int levelId) {
            this.employeeDto.levelId = levelId;
            return this;
        }

        public EmployeeDto.Builder setStatusId(int statusId) {
            this.employeeDto.statusId = statusId;
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

        public EmployeeDto.Builder setHours(double hours) {
            this.employeeDto.hours = hours;
            return this;
        }

        public EmployeeDto build() {
            return employeeDto;
        }
    }
}
