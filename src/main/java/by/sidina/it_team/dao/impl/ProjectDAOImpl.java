package by.sidina.it_team.dao.impl;

import by.sidina.it_team.dao.connection.ConnectionPool;
import by.sidina.it_team.dao.dto.ProjectDto;
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
            = "INSERT INTO projects (name, start_date, end_date, project_status_id, requirement_comment,customer_id) " +
            "values(?,?,?,?,?,?)";
    private static final String SQL_FIND_ALL_PROJECTS
            = """
            SELECT projects.id as id,
                   projects.name as name,
                   projects.start_date as start_date,
                   projects.end_date as end_date,
                   status_project.status as project_status,
                   projects.requirement_comment as comment,
                   projects.customer_id as customer_id,
                   sum(payments.amount) as payments,
                   sum(team_schedule.hours_fact) as hours_fact,
                   project_calculation.hours_plan as hours_plan,
                   project_calculation.cost_plan as cost_plan
            FROM projects
                     LEFT JOIN status_project ON projects.project_status_id = status_project.id
                     LEFT JOIN payments ON projects.id = payments.project_id
                     LEFT JOIN team_schedule ON projects.id = team_schedule.project_id
                     LEFT JOIN project_calculation ON projects.id = project_calculation.project_id
                     GROUP BY id
            """;
    private static final String SQL_FIND_PROJECTS_BY_CUSTOMER_ID
            = """
            SELECT projects.id as id,
                   projects.name as name,
                   projects.start_date as start_date,
                   projects.end_date as end_date,
                   status_project.status as project_status,
                   projects.requirement_comment as comment,
                   projects.customer_id as customer_id,
                   sum(payments.amount) as payments,
                   sum(team_schedule.hours_fact) as hours_fact,
                   project_calculation.hours_plan as hours_plan,
                   project_calculation.cost_plan as cost_plan
            FROM projects
                     LEFT JOIN status_project ON projects.project_status_id = status_project.id
                     LEFT JOIN payments ON projects.id = payments.project_id
                     LEFT JOIN team_schedule ON projects.id = team_schedule.project_id
                     LEFT JOIN project_calculation ON projects.id = project_calculation.project_id
            WHERE projects.customer_id=?
            GROUP BY id
            """;
    private static final String SQL_FIND_PROJECTS_BY_EMPLOYEE_ID
            = """
            SELECT projects.id as id,
                   projects.name as name,
                   projects.start_date as start_date,
                   projects.end_date as end_date,
                   status_project.status as project_status,
                   projects.requirement_comment as comment,
                   projects.customer_id as customer_id,
                   sum(payments.amount) as payments,
                   sum(team_schedule.hours_fact) as hours_fact,
                   project_calculation.hours_plan as hours_plan,
                   project_calculation.cost_plan as cost_plan
            FROM projects
                     LEFT JOIN status_project ON projects.project_status_id = status_project.id
                     LEFT JOIN payments ON projects.id = payments.project_id
                     LEFT JOIN team_schedule ON projects.id = team_schedule.project_id
                     LEFT JOIN project_calculation ON projects.id = project_calculation.project_id
            WHERE team_schedule.employee_id=?
            GROUP BY id
            """;
    private static final String SQL_FIND_PROJECT_BY_ID
            = "SELECT id, name, start_date, end_date, project_status_id, requirement_comment FROM projects WHERE id=?";

    @Override
    public List<ProjectDto> findAllForAdmin() throws DAOException {
        List<ProjectDto> projects = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PROJECTS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(retrieve(resultSet));
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
        return projects;
    }

    @Override
    public List<ProjectDto> findAllByCustomerID(int id) throws DAOException {
        List<ProjectDto> projects = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PROJECTS_BY_CUSTOMER_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(retrieve(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find projects by customer ID in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return projects;
    }

    public List<ProjectDto> findAllByEmployeeID(int id) throws DAOException {
        List<ProjectDto> projects = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PROJECTS_BY_EMPLOYEE_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(retrieve(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find projects by employee ID in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return projects;
    }

    @Override
    public Optional<ProjectDto> findByID(int id) throws DAOException {
        Optional<ProjectDto> optional = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PROJECT_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ProjectDto project = retrieve(resultSet);
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
            statement.setInt(6, project.getCustomerID());
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

    @Override
    public boolean update(ProjectDto project) throws DAOException {
        throw new DAOException("Not implemented");
    }

    private ProjectDto retrieve(ResultSet resultSet) throws SQLException {
        return new ProjectDto.Builder()
                .setCustomerId(resultSet.getInt("customer_id"))
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setStatus(resultSet.getString("project_status"))
                .setStartDate(resultSet.getDate("start_date"))
                .setEndDate(resultSet.getDate("end_date"))
                .setHoursPlan(resultSet.getDouble("hours_plan"))
                .setHoursFact(resultSet.getDouble("hours_fact"))
                .setAmount(resultSet.getDouble("payments"))
                .setCostPlan(resultSet.getDouble("cost_plan"))
                .build();

    }
}
