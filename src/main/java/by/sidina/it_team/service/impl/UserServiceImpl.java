package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.UserService;
import by.sidina.it_team.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public List<CustomerDto> findAllCustomers(int limit, int offset) throws ServiceException {
        List<CustomerDto> customers;
        try {
            customers = userDAO.findAllCustomers(limit, offset);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return customers;
    }

    @Override
    public Optional<User> findByID(int id) throws ServiceException {
        Optional<User> optional = Optional.empty();
        try {
            optional = userDAO.findByID(id);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return optional;
    }

    @Override
    public Optional<CustomerDto> findCustomerByID(int id) throws ServiceException {
        Optional<CustomerDto> optional = Optional.empty();
        try {
            optional = userDAO.findCustomerByID(id);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return optional;
    }

    @Override
    public int countAllCustomersForAdmin() throws ServiceException {
        int countCustomers = 0;
        try {
            countCustomers = userDAO.countAllCustomersForAdmin();
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return countCustomers;
    }

    @Override
    public boolean add(User user, String password) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = userDAO.add(user, password);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean edit(int id, User user) throws ServiceException {
        boolean isChanged = false;
        try {
            isChanged = userDAO.edit(id, user);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isChanged;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        Optional<User> optional = Optional.empty();
        try {
            optional = userDAO.findUserByEmail(email);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return optional;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        Optional<User> optional = Optional.empty();
        try {
            optional = userDAO.findUserByEmailAndPassword(email, password);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return optional;
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) throws ServiceException {
        Optional<String> optional = Optional.empty();
        try {
            optional = userDAO.findPasswordByEmail(email);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return optional;
    }

    @Override
    public boolean changeStatus(int id, int status) throws ServiceException {
        boolean isChanged = false;
        try {
            isChanged = userDAO.changeStatus(id, status);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isChanged;
    }
}
