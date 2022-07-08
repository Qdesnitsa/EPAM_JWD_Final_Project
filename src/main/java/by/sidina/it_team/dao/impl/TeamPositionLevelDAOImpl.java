package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamPositionLevelDAOImpl implements TeamPositionLevelDAO {
    private static final String SQL_FIND_ALL_EMPLOYEES
            = """
            SELECT team_position_level.employee_id,
                users.name AS name,
                users.surname AS surname,
                roles.role_types AS role,
                users.email AS email,
                status_user.status AS status,
                levels.level AS level,
                positions.position AS position
            FROM team_position_level
                LEFT JOIN users ON users.id=team_position_level.employee_id
                LEFT JOIN roles ON roles.id=users.role_id
                LEFT JOIN status_user ON status_user.id=users.user_status_id
                LEFT JOIN levels ON levels.id=team_position_level.employee_level_id
                Left JOIN positions ON positions.id=team_position_level.employee_position_id
            """;
    private static final String SQL_ADD_POSITION_LEVEL
            = "INSERT INTO team_position_level (employee_id, employee_position, employee_level) values(?,?,?)";
    private static final String SQL_FIND_EMPLOYEE_BY_ID
            = """
            SELECT team_position_level.employee_id,
                users.name AS name,
                users.surname AS surname,
                roles.role_types AS role,
                users.email AS email,
                status_user.status AS status,
                levels.level AS level,
                positions.position AS position
            FROM team_position_level
                LEFT JOIN users ON users.id=team_position_level.employee_id
                LEFT JOIN roles ON roles.id=users.role_id
                LEFT JOIN status_user ON status_user.id=users.user_status_id
                LEFT JOIN levels ON levels.id=team_position_level.employee_level_id
                Left JOIN positions ON positions.id=team_position_level.employee_position_id
            WHERE team_position_level.employee_id=?
            """;

    @Override
    public boolean add(TeamPositionLevel userPositionLevel, User user) throws DAOException {
        UserDAO userDAO = new UserDAOImpl();
        Optional<User> userOptional = userDAO.findUserByEmail(user.getEmail());
        boolean isAdded;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_POSITION_LEVEL)) {
            statement.setInt(1, userOptional.get().getId());
            statement.setInt(2, userPositionLevel.getEmployeePositionID());
            statement.setInt(2, userPositionLevel.getEmployeeLevelID());
            int counter = statement.executeUpdate();
            if (counter != 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to add new user position and level to the database", e);
        }
        return isAdded;
    }

    @Override
    public Optional<EmployeeDto> findByID(int id) throws DAOException {
        Optional<EmployeeDto> optional = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_EMPLOYEE_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                EmployeeDto employee = retrieveEmployee(resultSet);
                optional = Optional.of(employee);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find employee by ID in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return optional;
    }

    @Override
    public List<EmployeeDto> findAll() throws DAOException {
        List<EmployeeDto> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_EMPLOYEES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(retrieveEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find all employees in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return users;
    }

    private EmployeeDto retrieveEmployee(ResultSet resultSet) throws SQLException {
        return new EmployeeDto.Builder()
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setRole(Role.valueOf(resultSet.getString("role").toUpperCase()))
                .setEmail(resultSet.getString("email"))
                .setStatus(UserStatus.valueOf(resultSet.getString("status").toUpperCase()))
                .setLevel(Level.valueOf(resultSet.getString("level").toUpperCase()))
                .setPosition(String.valueOf(resultSet.getString("position")))
                .build();
    }
}
