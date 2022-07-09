package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.TeamScheduleDAO;
import by.sidina.it_team.entity.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamScheduleDAOImpl implements TeamScheduleDAO {
    private static final String SQL_ADD_EMPLOYEE_TO_PROJECT
            = "INSERT INTO team_schedule (employee_id, project_id, date, hours_fact) " +
            "values(?,?,(SELECT projects.start_date FROM projects WHERE id=?),0)";
    private static final String SQL_GET_PROJECT_START_DATE
            = "SELECT start_date FROM projects WHERE id=?";
    private static final String SQL_REMOVE_EMPLOYEE_FROM_PROJECT
            = "DELETE FROM team_schedule WHERE employee_id=? AND project_id=?";
    private static final String SQL_FIND_FREE_EMPLOYEES_FOR_PROJECT_ID
            = """
            SELECT team_position_level.employee_id AS id,
            	    positions.position AS position,
            		levels.level AS level
            FROM team_position_level
            LEFT JOIN team_schedule
            	ON team_position_level.employee_id=team_schedule.employee_id
            LEFT JOIN positions
            	ON team_position_level.employee_position_id=positions.id
            LEFT JOIN levels
            	ON team_position_level.employee_level_id=levels.id
            WHERE team_schedule.employee_id IS NULL
            AND position=?
            AND level=?
            UNION
            SELECT team_schedule.employee_id,
            		 positions.position,
            		 levels.level
            FROM team_schedule
            LEFT JOIN projects
            	ON team_schedule.project_id=projects.id
            LEFT JOIN team_position_level
            	ON team_position_level.employee_id=team_schedule.employee_id
            LEFT JOIN positions
            	ON team_position_level.employee_position_id=positions.id
            LEFT JOIN levels
            	ON team_position_level.employee_level_id=levels.id
            	WHERE position=?
            	AND level=?
            AND (projects.start_date <
            	(SELECT projects.start_date
            	FROM projects
            	WHERE projects.id=?)
            		AND projects.end_date <
            	(SELECT projects.start_date
            	FROM projects
            	WHERE projects.id=?)
            		OR projects.start_date >
            	(SELECT projects.end_date
            	FROM projects
            	WHERE projects.id=?)
            		AND projects.end_date >
            	(SELECT projects.end_date
            	FROM projects
                WHERE projects.id=?))
            LIMIT ?
            """;

    @Override
    public boolean addEmployeeToProject(int employee_id, int project_id) throws DAOException {
        boolean isAdded = false;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_EMPLOYEE_TO_PROJECT)) {
            //Optional<Date> startDate = getStartDate(project_id);
            statement.setInt(1, employee_id);
            statement.setInt(2, project_id);
            statement.setInt(3, project_id);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                isAdded = true;
                //LOGGER.info("New employee to project has been added:");
            } else {
                //LOGGER.error("New employee to project has not been added");
            }
        } catch (SQLException e) {
            //LOGGER.error("Failed attempt to add new application in the database");
            throw new DAOException("Failed attempt to add employee to project to the database", e);
        }
        return isAdded;
    }

    @Override
    public boolean removeEmployeeFromProject(int employee_id, int project_id) throws DAOException {
        boolean isRemoved = false;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_EMPLOYEE_FROM_PROJECT)) {
            statement.setInt(1, employee_id);
            statement.setInt(2, project_id);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                isRemoved = true;
                //LOGGER.info("Employee has been removed from project");
            } else {
                //LOGGER.info("Employee has not been removed from project");
            }
        } catch (SQLException e) {
            //LOGGER.error("Failed attempt to remove employee from project in the database");
            throw new DAOException("Failed attempt to remove employee from project in the database", e);
        }
        return isRemoved;
    }

    @Override
    public List<EmployeeDto> findFreeEmployeesForProject(int projectId, String position, Level level, int limit) throws DAOException {
        List<EmployeeDto> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_FREE_EMPLOYEES_FOR_PROJECT_ID)) {
            statement.setString(1, position);
            statement.setString(2, String.valueOf(level));
            statement.setString(3, position);
            statement.setString(4, String.valueOf(level));
            statement.setInt(5, projectId);
            statement.setInt(6, projectId);
            statement.setInt(7, projectId);
            statement.setInt(8, projectId);
            statement.setInt(9, limit);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(retrieveFreeEmployee(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find free employees in the database", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return users;
    }

    private EmployeeDto retrieveFreeEmployee(ResultSet resultSet) throws SQLException {
        return new EmployeeDto.Builder()
                .setId(resultSet.getInt("id"))
                .setPosition(String.valueOf(resultSet.getString("position")))
                .setLevel(Level.valueOf(resultSet.getString("level").toUpperCase()))
                .build();
    }
}
