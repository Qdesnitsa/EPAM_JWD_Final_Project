package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.entity.UserStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO<User,UserStatus,String> {
    private static final String SQL_FIND_ALL_USERS
            = "SELECT id, name, surname, (SELECT role_types from roles WHERE id=users.role_id), email, " +
            "(SELECT status from status_user WHERE id=users.user_status_id) FROM users";
    private static final String SQL_FIND_USER_BY_ID
            = "SELECT id, name, surname, (SELECT role_types from roles WHERE id=users.role_id), email, " +
            "(SELECT status from status_user WHERE id=users.user_status_id) FROM users WHERE id=?";
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

    public static void main(String[] args) throws DAOException {
        UserDAOImpl userDAO = new UserDAOImpl();
        List<User> list = userDAO.findAll();
        System.out.println(list);
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(retrieve(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find all users in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return users;
    }

    @Override
    public Optional<User> findByID(int id) throws DAOException {
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
            throw new DAOException("Failed attempt to find user by user ID in the database", e);
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
    public synchronized boolean add(User user, String password) throws DAOException {
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
            } else {
                isAdded = false;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to add new user to the database", e);
        }
        return isAdded;
    }

    @Override
    public boolean edit(int id, User user) throws DAOException {
        boolean isChanged = false;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EDIT_USER_STATUS_BY_ID)) {
            statement.setInt(1, user.getStatus_id());
            statement.setInt(2, id);
            int counter = statement.executeUpdate();
            if (counter != 0) {
                isChanged = true;
            } else {
                isChanged = false;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to change user status in the database", e);
        }
        return isChanged;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DAOException {
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
            throw new DAOException("Failed attempt to find user by email in the database", e);
        }
        return optional;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DAOException {
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
            throw new DAOException("Failed attempt to find user by email and password in the database", e);
        }
        return optional;
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) throws DAOException {
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
            throw new DAOException("Failed attempt to find password by email in the database", e);
        }
        return optional;
    }

    private User retrieve(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setId(resultSet.getInt("role_id"))
                .setEmail(resultSet.getString("email"))
                .setStatus_id(resultSet.getInt("user_status_id"))
                .build();
    }
}
