package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.ProjectCalculationDAO;

import java.sql.*;

public class ProjectCalculationDAOImpl implements ProjectCalculationDAO {
    private static final int WORKING_HOURS_PER_DAY = 8;
    private static final String SQL_GET_PROJECT_WORKING_DAYS
            = "SELECT start_date, end_date FROM projects WHERE id=?";
    private static final String SQL_GET_SUM_AND_COUNT_RATES
            = """
            SELECT sum(rates.rate) AS sum_rates,
                   count(team_schedule.employee_id) AS count_employees
            FROM   team_schedule
                   LEFT JOIN team_position_level ON team_schedule.employee_id=team_position_level.employee_id
                   LEFT JOIN rates ON (team_position_level.employee_position_id=rates.position_id AND 
                                        team_position_level.employee_level_id=rates.level_id)
            WHERE team_schedule.project_id=?                      
            """;
    private static final String SQL_ADD_PROJECT_CALCULATION
            = "INSERT INTO project_calculation(project_id,hours_plan,cost_plan) values(?,?,?)";

    @Override
    public boolean add(int project_id) throws DAOException {
        boolean isAdded;
        int workingDays = countProjectWorkDays(project_id);
        int numberEmployees = countProjectNumberEmployees(project_id);
        double costPerHour = countProjectCostPerDay(project_id);
        int totalHoursOnProject = numberEmployees * WORKING_HOURS_PER_DAY * workingDays;
        double totalCostOfProject = costPerHour * WORKING_HOURS_PER_DAY * workingDays;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_PROJECT_CALCULATION)) {
            statement.setInt(1, project_id);
            statement.setInt(2, totalHoursOnProject);
            statement.setDouble(3, totalCostOfProject);
            int counter = statement.executeUpdate();
            if (counter != 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to add project calculation to the database", e);
        }
        return isAdded;
    }

    private static int countProjectWorkDays(int project_id) throws DAOException {
        ResultSet resultSet = null;
        int workingDays = 0;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_PROJECT_WORKING_DAYS)) {
            statement.setInt(1, project_id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                workingDays = countWorkDays(startDate, endDate);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find start and end days of project in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return workingDays;
    }

    private static double countProjectCostPerDay(int project_id) throws DAOException {
        ResultSet resultSet = null;
        double costPerDay = 0;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_SUM_AND_COUNT_RATES)) {
            statement.setInt(1, project_id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                costPerDay = resultSet.getDouble("sum_rates");
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find rates for project in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return costPerDay;
    }

    private static int countProjectNumberEmployees(int project_id) throws DAOException {
        ResultSet resultSet = null;
        int numberEmployees = 0;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_SUM_AND_COUNT_RATES)) {
            statement.setInt(1, project_id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numberEmployees = resultSet.getInt("count_employees");
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find rates for project in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return numberEmployees;
    }

    private static int countWorkDays(Date strStartDate, Date strEndDate) throws DAOException {
        int countWorkDays = 0;
        while (strStartDate.compareTo(strEndDate) <= 0) {
            if (strStartDate.getDay() != 6 && strEndDate.getDay() != 0)
                ++countWorkDays;
            strStartDate.setDate(strStartDate.getDate() + 1);
        }
        return countWorkDays;
    }
}
