package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.TeamPositionLevelService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TeamPositionLevelServiceImpl implements TeamPositionLevelService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final TeamPositionLevelDAO teamPositionLevelDAO;

    public TeamPositionLevelServiceImpl(TeamPositionLevelDAO teamPositionLevelDAO) {
        this.teamPositionLevelDAO = teamPositionLevelDAO;
    }

    @Override
    public boolean add(String position, String level, User user, String password) throws ServiceException {
        boolean isAdded = false;
        if (Validator.isValidPosition(position) && Validator.isValidLevel(level) && Validator.isValidPassword(password)) {
            try {
                isAdded = teamPositionLevelDAO.add(Integer.parseInt(position), Integer.parseInt(level), user, password);
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to add new employee from DAO", e);
            }
        }
        return isAdded;
    }

    @Override
    public Optional<EmployeeDto> findByID(int id) throws ServiceException {
        Optional<EmployeeDto> optional = Optional.empty();
        try {
            optional = teamPositionLevelDAO.findByID(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find employee by ID from DAO", e);
        }
        return optional;
    }

    @Override
    public List<EmployeeDto> findAllForAdmin(int limit, int offset) throws ServiceException {
        List<EmployeeDto> employees;
        try {
            employees = teamPositionLevelDAO.findAllForAdmin(limit, offset);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find all employees from DAO", e);
        }
        return employees;
    }

    @Override
    public boolean changePosition(int id, String position) throws ServiceException {
        boolean isChanged = false;
        if (Validator.isValidPosition(position)) {
            try {
                isChanged = teamPositionLevelDAO.changePosition(id, Integer.parseInt(position));
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to change position of employee from DAO", e);
            }
        }
        return isChanged;
    }

    @Override
    public boolean changeLevel(int id, String level) throws ServiceException {
        boolean isChanged = false;
        if (Validator.isValidLevel(level)) {
            try {
                isChanged = teamPositionLevelDAO.changeLevel(id, Integer.parseInt(level));
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to change level of employee from DAO", e);
            }
        }
        return isChanged;
    }

    @Override
    public int countAllEmployeesForAdmin() throws ServiceException {
        int countCustomers = 0;
        try {
            countCustomers = teamPositionLevelDAO.countAllEmployeesForAdmin();
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to count all employees from DAO", e);
        }
        return countCustomers;
    }
}
