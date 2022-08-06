package by.sidina.it_team.service.repository;

import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll() throws ServiceException;

    List<CustomerDto> findAllCustomers(int limit, int offset) throws ServiceException;

    Optional<User> findByID(int id) throws ServiceException;

    Optional<CustomerDto> findCustomerByID(int id) throws ServiceException;

    int countAllCustomersForAdmin() throws ServiceException;

    boolean add(User user, String password) throws ServiceException;

    boolean edit(int id, User user) throws ServiceException;

    Optional<User> findUserByEmail(String email) throws ServiceException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    Optional<String> findPasswordByEmail(String email) throws ServiceException;

    boolean changeStatus(int id, int status) throws ServiceException;
}
