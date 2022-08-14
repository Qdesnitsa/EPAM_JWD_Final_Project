package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.PaymentDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SQL_ADD_PAYMENT
            = "INSERT INTO payments (project_id, amount, payment_date) values(?,?,?)";

    @Override
    public boolean addPaymentByProjectAndCustomerID(ProjectDto projectDto, double amount, Date date) throws DAOException {
        LOGGER.info("Attempt to add new project to the database");
        boolean isAdded;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_PAYMENT)) {
            statement.setInt(1, projectDto.getId());
            statement.setDouble(2, amount);
            statement.setDate(3, date);
            int counter = statement.executeUpdate();
            if (counter != 0) {
                isAdded = true;
                LOGGER.info("New project has been added");
            } else {
                isAdded = false;
                LOGGER.error("New project has not been added");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException("Failed attempt to add new project to the database", e);
        }
        return isAdded;
    }
}
