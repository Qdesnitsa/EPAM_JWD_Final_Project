package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectDAO {
    List<Project> findAll() throws DAOException;
    Optional<Project> findByID(int id) throws DAOException;
    boolean add(Project project) throws DAOException;
}
