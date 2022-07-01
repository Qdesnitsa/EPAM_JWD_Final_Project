package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface UserDAO<T,N,S> {
    List<T> findAll() throws DAOException;
    Optional<T> findByID(int id) throws DAOException;
    boolean edit(int id, T entity) throws DAOException;
    boolean add(T user, S password) throws DAOException;
    Optional<T> findUserByEmail(S email) throws DAOException;
    Optional<T> findUserByEmailAndPassword(S email, S password) throws DAOException;
    Optional<S> findPasswordByEmail(S email) throws DAOException;
}
