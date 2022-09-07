package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.entity.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SQL_FIND_ALL_USERS
            = """
            SELECT id,
                   name,
                   surname,
                   (SELECT role_types
                   FROM   roles
                   WHERE  id = users.role_id),
                   email,
                   (SELECT status
                   FROM   status_user
                   WHERE  id = users.user_status_id)
            FROM   users
            """;
    private static final String SQL_FIND_ALL_CUSTOMERS
            = """
            SELECT users.id           AS id,
                   users.name         AS name,
                   users.surname      AS surname,
                   roles.role_types   AS role,
                   users.email        AS email,
                   status_user.status AS status,
                   status_user.id     AS status_id
            FROM   users
                   LEFT JOIN roles
                          ON roles.id = users.role_id
                   LEFT JOIN status_user
                          ON status_user.id = users.user_status_id
            WHERE  roles.role_types = "customer"
            ORDER  BY status_user.status
            LIMIT  ? offset ?
            """;
    private static final String SQL_FIND_CUSTOMER_BY_ID
            = """
            SELECT users.id           AS id,
                   users.name         AS name,
                   users.surname      AS surname,
                   roles.role_types   AS role,
                   users.email        AS email,
                   status_user.status AS status,
                   status_user.id     AS status_id
            FROM   users
                   LEFT JOIN roles
                          ON roles.id = users.role_id
                   LEFT JOIN status_user
                          ON status_user.id = users.user_status_id
            WHERE  roles.role_types = "customer"
                   AND users.id =?
            ORDER  BY status_user.status
            """;
    private static final String SQL_COUNT_ALL_CUSTOMERS
            = """
            SELECT count(*) AS count
            FROM   users
            WHERE  role_id = 3
            AND (name LIKE ? OR surname LIKE ?)
            """;
    private static final String SQL_FIND_USER_BY_ID
            = """
            SELECT id,
                   name,
                   surname,
                   (SELECT role_types
                    FROM   roles
                    WHERE  id = users.role_id),
                   email,
                   (SELECT status
                    FROM   status_user
                    WHERE  id = users.user_status_id)
            FROM   users
            WHERE  id =?
            """;
    private static final String SQL_ADD_EMPLOYEE
            = "INSERT INTO users (name, surname, role_id, email, password, user_status_id) values(?,?,?,?,?,?)";
    private static final String SQL_EDIT_USER_STATUS_BY_ID
            = "UPDATE users SET user_status_id=? WHERE id=?";
    private static final String SQL_FIND_USER_BY_EMAIL =
            "SELECT id, name, surname, role_id, email, user_status_id FROM users WHERE email=?";
    private static final String SQL_FIND_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT id, name, surname, role_id, email, user_status_id FROM users WHERE email=? AND password=?";
    private static final String SQL_FIND_PASSWORD_BY_EMAIL
            = "SELECT password FROM users WHERE email=?";
    private static final String SQL_FIND_NAMES_AND_SURNAMES_BY_PATTERN
            = """
            SELECT users.id           AS id,
                   users.name         AS name,
                   users.surname      AS surname,
                   roles.role_types   AS role,
                   users.email        AS email,
                   status_user.status AS status,
                   status_user.id     AS status_id
            FROM   users
                   LEFT JOIN roles
                          ON roles.id = users.role_id
                   LEFT JOIN status_user
                          ON status_user.id = users.user_status_id
            WHERE  roles.role_types = "customer"
                   AND (users.name LIKE ? OR users.surname LIKE ?)
            ORDER  BY status_user.status
            LIMIT  ? offset ?
            """;

    @Override
    public List<User> findAll() throws DAOException {
        LOGGER.info("Attempt to find all users in the database");
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(retrieve(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find all users in the database", e);
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
    public List<CustomerDto> findAllCustomers(int limit, int offset) throws DAOException {
        LOGGER.info("Attempt to find all customers in the database");
        List<CustomerDto> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_CUSTOMERS)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(retrieveDto(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find all customers in the database", e);
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
    public List<CustomerDto> findAllCustomersByPattern(int limit, int offset, String pattern) throws DAOException {
        LOGGER.info("Attempt to find all customers by pattern in the database");
        List<CustomerDto> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_NAMES_AND_SURNAMES_BY_PATTERN)) {
            statement.setString(1, "%" + pattern + "%");
            statement.setString(2, "%" + pattern + "%");
            statement.setInt(3, limit);
            statement.setInt(4, offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(retrieveDto(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find all customers by pattern in the database", e);
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
    public Optional<User> findByID(int id) throws DAOException {
        LOGGER.info("Attempt to find user by user ID in the database");
        Optional<User> optional = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = retrieve(resultSet);
                optional = Optional.of(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find user by user ID in the database", e);
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
    public Optional<CustomerDto> findCustomerByID(int id) throws DAOException {
        LOGGER.info("Attempt to find customer by user ID in the database");
        Optional<CustomerDto> optional = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_CUSTOMER_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CustomerDto customer = retrieveDto(resultSet);
                optional = Optional.of(customer);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find customer by user ID in the database", e);
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
    public int countAllCustomersForAdmin(String pattern) throws DAOException {
        LOGGER.info("Attempt to count all customers in the database");
        int countCustomers = 0;
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COUNT_ALL_CUSTOMERS)) {
            statement.setString(1, "%" + pattern + "%");
            statement.setString(2, "%" + pattern + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                countCustomers = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to count all customers in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return countCustomers;
    }

    @Override
    public boolean add(User user, String password) throws DAOException {
        LOGGER.info("Attempt to add new user to the database");
        boolean isAdded;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_EMPLOYEE)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getRole_id());
            statement.setString(4, user.getEmail());
            statement.setString(5, password);
            statement.setInt(6, user.getStatus_id());
            int counter = statement.executeUpdate();
            if (counter != 0) {
                isAdded = true;
                LOGGER.info("New user has been added");
            } else {
                isAdded = false;
                LOGGER.error("New user has not been added");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to add new user to the database", e);
        }
        return isAdded;
    }

    @Override
    public boolean edit(int id, User user) throws DAOException {
        LOGGER.info("Attempt to change user status in the database");
        boolean isChanged = false;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EDIT_USER_STATUS_BY_ID)) {
            statement.setInt(1, user.getStatus_id());
            statement.setInt(2, id);
            int counter = statement.executeUpdate();
            if (counter != 0) {
                isChanged = true;
                LOGGER.info("User status was changed");
            } else {
                isChanged = false;
                LOGGER.error("User status has not been changed");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to change user status in the database", e);
        }
        return isChanged;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DAOException {
        LOGGER.info("Attempt to find user by email in the database");
        Optional<User> optional;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = retrieve(resultSet);
                optional = Optional.of(user);
            } else {
                optional = Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find user by email in the database", e);
        }
        return optional;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DAOException {
        LOGGER.info("Attempt to find user by email and password in the database");
        Optional<User> optional;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = retrieve(resultSet);
                optional = Optional.of(user);
            } else {
                optional = Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find user by email and password in the database", e);
        }
        return optional;
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) throws DAOException {
        LOGGER.info("Attempt to find password by email in the database");
        Optional<String> optional;
        String password;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString("password");
                optional = Optional.of(password);
            } else {
                optional = Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to find password by email in the database", e);
        }
        return optional;
    }

    @Override
    public boolean changeStatus(int id, int status) throws DAOException {
        LOGGER.info("Attempt to change employee status in the database");
        boolean isChanged = false;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EDIT_USER_STATUS_BY_ID)) {
            statement.setInt(1, status);
            statement.setInt(2, id);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                isChanged = true;
                LOGGER.info("User status has been changed");
            } else {
                LOGGER.error("User status has not been changed");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to change employee status in the database", e);
        }
        return isChanged;
    }

    private User retrieve(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setRole_id(resultSet.getInt("role_id"))
                .setEmail(resultSet.getString("email"))
                .setStatus_id(resultSet.getInt("user_status_id"))
                .build();
    }

    private CustomerDto retrieveDto(ResultSet resultSet) throws SQLException {
        return new CustomerDto.Builder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setEmail(resultSet.getString("email"))
                .setStatus(UserStatus.valueOf(resultSet.getString("status")))
                .setStatusId(Integer.parseInt(resultSet.getString("status_id")))
                .build();
    }
}
