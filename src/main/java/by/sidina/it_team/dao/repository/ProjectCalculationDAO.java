package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.exception.DAOException;

public interface ProjectCalculationDAO {
    boolean add(int projectId) throws DAOException;
    boolean remove(int projectId) throws DAOException;
}