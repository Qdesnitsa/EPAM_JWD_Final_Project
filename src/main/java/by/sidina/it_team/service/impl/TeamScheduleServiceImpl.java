package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.TeamScheduleDAO;
import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.TeamSchedule;
import by.sidina.it_team.service.repository.TeamScheduleService;
import by.sidina.it_team.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TeamScheduleServiceImpl implements TeamScheduleService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final TeamScheduleDAO teamScheduleDAO;

    public TeamScheduleServiceImpl(TeamScheduleDAO teamScheduleDAO) {
        this.teamScheduleDAO = teamScheduleDAO;
    }

    @Override
    public boolean addEmployeeToProject(int employeeId, int projectId) throws ServiceException {
        boolean isAdded = false;
        try {
            isAdded = teamScheduleDAO.addEmployeeToProject(employeeId, projectId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to add employee to project from DAO", e);
        }
        return isAdded;
    }

    @Override
    public boolean removeEmployeeFromProject(int employeeId, int projectId) throws ServiceException {
        boolean isRemoved = false;
        try {
            isRemoved = teamScheduleDAO.removeEmployeeFromProject(employeeId, projectId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to remove employee from project from DAO", e);
        }
        return isRemoved;
    }

    @Override
    public List<EmployeeDto> findEmployeesOnProject(int projectId) throws ServiceException {
        List<EmployeeDto> employees = new ArrayList<>();
        try {
            employees = teamScheduleDAO.findEmployeesOnProject(projectId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find employees on project from DAO", e);
        }
        return employees;
    }

    @Override
    public List<EmployeeDto> findFreeEmployeesForProject(int projectId, String position, Level level, int limit) throws ServiceException {
        List<EmployeeDto> employees;
        try {
            employees = teamScheduleDAO.findFreeEmployeesForProject(projectId, position, level, limit);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find free employee for project from DAO", e);
        }
        return employees;
    }

    @Override
    public boolean addHoursByEmployeeId(TeamSchedule teamSchedule) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = teamScheduleDAO.addHoursByEmployeeId(teamSchedule);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to add hours to project by employee ID from DAO", e);
        }
        return isAdded;
    }
}
