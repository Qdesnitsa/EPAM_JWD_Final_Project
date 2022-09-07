package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.UserService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();
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
            LOGGER.error(e);
            throw new ServiceException("Failed to find all users from DAO", e);
        }
        return users;
    }

    @Override
    public List<CustomerDto> findAllCustomers(int limit, int offset) throws ServiceException {
        List<CustomerDto> customers;
        try {
            customers = userDAO.findAllCustomers(limit, offset);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find all customers from DAO", e);
        }
        return customers;
    }

    @Override
    public List<CustomerDto> findAllCustomersByPattern(int limit, int offset, String pattern) throws ServiceException {
        List<CustomerDto> customers;
        try {
            customers = userDAO.findAllCustomersByPattern(limit, offset, pattern);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find all customers by pattern from DAO", e);
        }
        return customers;
    }

    @Override
    public Optional<User> findByID(int id) throws ServiceException {
        Optional<User> optional = Optional.empty();
        try {
            optional = userDAO.findByID(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find user by ID from DAO", e);
        }
        return optional;
    }

    @Override
    public Optional<CustomerDto> findCustomerByID(int id) throws ServiceException {
        Optional<CustomerDto> optional = Optional.empty();
        try {
            optional = userDAO.findCustomerByID(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find customer by ID from DAO", e);
        }
        return optional;
    }

    @Override
    public int countAllCustomersForAdmin(String pattern) throws ServiceException {
        int countCustomers = 0;
        try {
            countCustomers = userDAO.countAllCustomersForAdmin(pattern);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to count all customers from DAO", e);
        }
        return countCustomers;
    }

    @Override
    public boolean add(User user, String password) throws ServiceException {
        boolean isAdded = false;
        if (Validator.isValidPassword(password) && user != null) {
            try {
                isAdded = userDAO.add(user, password);
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to add new user from DAO", e);
            }
        }
        return isAdded;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        Optional<User> optional = Optional.empty();
        if (Validator.isValidEmail(email)) {
            try {
                optional = userDAO.findUserByEmail(email);
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to find user by email from DAO", e);
            }
        } else {
            throw new ServiceException("Email is invalid");
        }
        return optional;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        Optional<User> optional;
        if (Validator.isValidEmail(email) && Validator.isValidPassword(password)) {
            try {
                optional = userDAO.findUserByEmailAndPassword(email, password);
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to find user by email and password from DAO", e);
            }
        } else {
            throw new ServiceException("Email and password are invalid");
        }
        return optional;
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) throws ServiceException {
        Optional<String> optional = Optional.empty();
        if (Validator.isValidEmail(email)) {
            try {
                optional = userDAO.findPasswordByEmail(email);
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to find password by email from DAO", e);
            }
        }
        return optional;
    }

    @Override
    public boolean changeStatus(int id, String status) throws ServiceException {
        boolean isChanged = false;
        if (Validator.isValidUserStatus(status)) {
            try {
                isChanged = userDAO.changeStatus(id, Integer.parseInt(status));
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to change user status from DAO", e);
            }
        }
        return isChanged;
    }
}
