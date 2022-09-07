package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll() throws DAOException;

    List<CustomerDto> findAllCustomers(int limit, int offset) throws DAOException;
    List<CustomerDto> findAllCustomersByPattern(int limit, int offset, String pattern) throws DAOException;

    Optional<CustomerDto> findCustomerByID(int id) throws DAOException;

    Optional<User> findByID(int id) throws DAOException;

    boolean edit(int id, User entity) throws DAOException;

    boolean add(User user, String password) throws DAOException;

    Optional<User> findUserByEmail(String email) throws DAOException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws DAOException;

    Optional<String> findPasswordByEmail(String email) throws DAOException;

    boolean changeStatus(int id, int status) throws DAOException;

    int countAllCustomersForAdmin(String pattern) throws DAOException;
}
