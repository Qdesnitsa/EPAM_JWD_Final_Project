package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.TeamPositionLevelService;
import by.sidina.it_team.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class TeamPositionLevelServiceImpl implements TeamPositionLevelService {
    private final TeamPositionLevelDAO teamPositionLevelDAO;

    public TeamPositionLevelServiceImpl(TeamPositionLevelDAO teamPositionLevelDAO) {
        this.teamPositionLevelDAO = teamPositionLevelDAO;
    }

    @Override
    public boolean add(int position, int level, User user, String password) throws ServiceException {
        boolean isAdded = false;
        try {
            isAdded = teamPositionLevelDAO.add(position, level, user, password);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public Optional<EmployeeDto> findByID(int id) throws ServiceException {
        Optional<EmployeeDto> optional = Optional.empty();
        try {
            optional = teamPositionLevelDAO.findByID(id);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return optional;
    }

    @Override
    public List<EmployeeDto> findAllForAdmin(int limit, int offset) throws ServiceException {
        List<EmployeeDto> employees;
        try {
            employees = teamPositionLevelDAO.findAllForAdmin(limit, offset);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return employees;
    }

    @Override
    public boolean changePosition(int id, int position) throws ServiceException {
        boolean isChanged = false;
        try {
            isChanged = teamPositionLevelDAO.changePosition(id, position);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isChanged;
    }

    @Override
    public boolean changeLevel(int id, int level) throws ServiceException {
        boolean isChanged = false;
        try {
            isChanged = teamPositionLevelDAO.changeLevel(id, level);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isChanged;
    }

    @Override
    public int countAllEmployeesForAdmin() throws ServiceException {
        int countCustomers = 0;
        try {
            countCustomers = teamPositionLevelDAO.countAllEmployeesForAdmin();
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return countCustomers;
    }
}
