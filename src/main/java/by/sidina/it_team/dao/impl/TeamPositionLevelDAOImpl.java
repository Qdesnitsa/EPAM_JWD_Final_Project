package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamPositionLevelDAOImpl implements TeamPositionLevelDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SQL_FIND_ALL_EMPLOYEES
            = """
            SELECT team_position_level.employee_id AS id,
                   users.name                      AS name,
                   users.surname                   AS surname,
                   roles.role_types                AS role,
                   users.email                     AS email,
                   status_user.status              AS status,
                   status_user.id                  AS status_id,
                   levels.level                    AS level,
                   levels.id                       AS level_id,
                   positions.position              AS position,
                   positions.id                    AS position_id
            FROM   team_position_level
                   LEFT JOIN users
                          ON users.id = team_position_level.employee_id
                   LEFT JOIN roles
                          ON roles.id = users.role_id
                   LEFT JOIN status_user
                          ON status_user.id = users.user_status_id
                   LEFT JOIN levels
                          ON levels.id = team_position_level.employee_level_id
                   LEFT JOIN positions
                          ON positions.id = team_position_level.employee_position_id
            ORDER  BY surname,
                      name
            LIMIT  ? offset ?
            """;
    private static final String SQL_ADD_POSITION_LEVEL
            = "INSERT INTO team_position_level (employee_id, employee_position_id, employee_level_id) values(?,?,?)";

    private static final String SQL_COUNT_ALL_EMPLOYEES
            = "SELECT count(*) AS count FROM team_position_level";
    private static final String SQL_FIND_EMPLOYEE_BY_ID
            = """
            SELECT team_position_level.employee_id AS id,
                   users.name                      AS name,
                   users.surname                   AS surname,
                   roles.role_types                AS role,
                   users.email                     AS email,
                   status_user.status              AS status,
                   status_user.id                  AS status_id,
                   levels.level                    AS level,
                   levels.id                       AS level_id,
                   positions.position              AS position,
                   positions.id                    AS position_id
            FROM   team_position_level
                   LEFT JOIN users
                          ON users.id = team_position_level.employee_id
                   LEFT JOIN roles
                          ON roles.id = users.role_id
                   LEFT JOIN status_user
                          ON status_user.id = users.user_status_id
                   LEFT JOIN levels
                          ON levels.id = team_position_level.employee_level_id
                   LEFT JOIN positions
                          ON positions.id = team_position_level.employee_position_id
            WHERE  team_position_level.employee_id =?
            """;

    private static final String SQL_CHANGE_EMPLOYEE_POSITION
            = "UPDATE team_position_level SET employee_position_id=? WHERE employee_id=?";
    private static final String SQL_CHANGE_EMPLOYEE_LEVEL
            = "UPDATE team_position_level SET employee_level_id=? WHERE employee_id=?";

    @Override
    public boolean add(int position, int level, User user, String password) throws DAOException {
        LOGGER.info("Attempt to add user and employee in 2 schemas to the database");
        UserDAO userDAO = new UserDAOImpl();
        boolean isPositionAndLevelAdded = false;
        boolean isUserAdded = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            isUserAdded = userDAO.add(user, password);
            Optional<User> userOptional = userDAO.findUserByEmail(user.getEmail());
            statement = connection.prepareStatement(SQL_ADD_POSITION_LEVEL);
            statement.setInt(1, userOptional.get().getId());
            statement.setInt(2, position);
            statement.setInt(3, level);
            int counter = statement.executeUpdate();
            if (counter != 0) {
                isPositionAndLevelAdded = true;
            } else {
                isPositionAndLevelAdded = false;
            }
            if (isUserAdded && isPositionAndLevelAdded) {
                LOGGER.info("User and employee has been added");
                connection.commit();
            } else {
                LOGGER.error("User and employee has not been added");
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex);
                throw new DAOException("Failed attempt to rollback operation of adding user/employee to the database", e);
            }
            LOGGER.error(e);
            throw new DAOException("Failed attempt to add user and employee in 2 schemas to the database", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
        return isPositionAndLevelAdded;
    }


    @Override
    public Optional<EmployeeDto> findByID(int id) throws DAOException {
        LOGGER.info("Attempt to find employee by ID in the database");
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
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find employee by ID in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return optional;
    }

    @Override
    public List<EmployeeDto> findAllForAdmin(int limit, int offset) throws DAOException {
        LOGGER.info("Attempt to find all employees in the database");
        List<EmployeeDto> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_EMPLOYEES)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(retrieveEmployee(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find all employees in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return users;
    }

    @Override
    public int countAllEmployeesForAdmin() throws DAOException {
        LOGGER.info("Attempt to count all employees in the database");
        int countEmployees = 0;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COUNT_ALL_EMPLOYEES)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                countEmployees = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to count all employees in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return countEmployees;
    }

    @Override
    public boolean changePosition(int id, int position) throws DAOException {
        LOGGER.info("Attempt to change employee position in the database");
        boolean isChanged = false;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_EMPLOYEE_POSITION)) {
            statement.setInt(1, position);
            statement.setInt(2, id);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                isChanged = true;
                LOGGER.info("Employee position has been changed");
            } else {
                LOGGER.error("Employee position has not been changed");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to change employee position in the database", e);
        }
        return isChanged;
    }

    @Override
    public boolean changeLevel(int id, int level) throws DAOException {
        LOGGER.info("Attempt to change employee level in the database");
        boolean isChanged = false;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_EMPLOYEE_LEVEL)) {
            statement.setInt(1, level);
            statement.setInt(2, id);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                isChanged = true;
                LOGGER.info("Employee level has been changed");
            } else {
                LOGGER.error("Employee level has not been changed");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to change employee level in the database", e);
        }
        return isChanged;
    }

    private EmployeeDto retrieveEmployee(ResultSet resultSet) throws SQLException {
        return new EmployeeDto.Builder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setRole(Role.valueOf(resultSet.getString("role").toUpperCase()))
                .setEmail(resultSet.getString("email"))
                .setStatus(UserStatus.valueOf(resultSet.getString("status").toUpperCase()))
                .setStatusId(Integer.parseInt(resultSet.getString("status_id")))
                .setLevel(Level.valueOf(resultSet.getString("level").toUpperCase()))
                .setLevelId(Integer.parseInt(resultSet.getString("level_id")))
                .setPosition(String.valueOf(resultSet.getString("position")))
                .setPositionId(Integer.parseInt(resultSet.getString("position_id")))
                .build();
    }
}
