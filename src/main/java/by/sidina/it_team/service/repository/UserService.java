package by.sidina.it_team.service.repository;

import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll() throws ServiceException;

    List<CustomerDto> findAllCustomers(int limit, int offset) throws ServiceException;
    List<CustomerDto> findAllCustomersByPattern(int limit, int offset, String pattern) throws ServiceException;

    Optional<User> findByID(int id) throws ServiceException;

    Optional<CustomerDto> findCustomerByID(int id) throws ServiceException;

    int countAllCustomersForAdmin(String pattern) throws ServiceException;

    boolean add(User user, String password) throws ServiceException;

    Optional<User> findUserByEmail(String email) throws ServiceException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    Optional<String> findPasswordByEmail(String email) throws ServiceException;

    boolean changeStatus(int id, String status) throws ServiceException;
}
