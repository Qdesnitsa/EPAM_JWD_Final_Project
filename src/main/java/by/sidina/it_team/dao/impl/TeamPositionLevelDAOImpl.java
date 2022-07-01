package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.TeamPositionLevel;
import by.sidina.it_team.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class TeamPositionLevelDAOImpl implements TeamPositionLevelDAO {
    private static final String SQL_ADD_POSITION_LEVEL
            = "INSERT INTO team_position_level (employee_id, employee_position, employee_level) values(?,?,?)";

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
}
