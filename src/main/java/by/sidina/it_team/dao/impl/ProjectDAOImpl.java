package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.entity.Project;
import by.sidina.it_team.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDAOImpl implements ProjectDAO {
    private static final String SQL_ADD_PROJECT
            = "INSERT INTO projects (name, start_date, end_date, project_status_id, requirement_comment) values(?,?,?,?,?)";
    private static final String SQL_FIND_ALL_PROJECTS
            = "SELECT pr.id, pr.name, pr.start_date, pr.end_date, " +
            "(SELECT status FROM status_project sp WHERE sp.id=projects.project_status_id) as project_status, " +
            "pa.customer_id, pa.amount, ts.hours_fact, pc.hours_plan, pc.cost_plan FROM projects pr LEFT JOIN payments pa " +
            "ON pr.id=pa.project_id LEFT JOIN team_schedule ts ON pr.id=ts.project_id LEFT JOIN project_calculation pc ON " +
            "pr.id=pc.project_id";
    private static final String SQL_FIND_PROJECT_BY_ID
            = "SELECT id, name, start_date, end_date, project_status_id, requirement_comment FROM projects WHERE id=?";

    @Override
    public List<Project> findAll() throws DAOException {
        List<Project> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PROJECTS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(retrieve(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find all projects in the database", e);
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
    public Optional<Project> findByID(int id) throws DAOException {
        Optional<Project> optional = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PROJECT_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Project project = retrieve(resultSet);
                optional = Optional.of(project);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find project by project ID in the database", e);
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
    public boolean add(Project project) throws DAOException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_PROJECT)) {
            statement.setString(1, project.getName());
            statement.setDate(2, project.getStartDate());
            statement.setDate(3, project.getEndDate());
            statement.setInt(4, 1);
            statement.setString(5, project.getRequirementComment());
            int counter = statement.executeUpdate();
            if (counter != 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to add new project to the database", e);
        }
        return isAdded;
    }

    private Project retrieve(ResultSet resultSet) throws SQLException {
        return new Project.Builder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setStartDate(resultSet.getDate("start_date"))
                .setEndDate(resultSet.getDate("end_date"))
                .setProjectStatusID(resultSet.getInt("project_status_id"))
                .setRequirementComment(resultSet.getString("requirement_comment"))
                .build();
    }
}
