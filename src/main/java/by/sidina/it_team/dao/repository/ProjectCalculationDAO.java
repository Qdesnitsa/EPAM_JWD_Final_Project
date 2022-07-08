package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.exception.DAOException;

public interface ProjectCalculationDAO {
    boolean add(int project_id) throws DAOException;
}
