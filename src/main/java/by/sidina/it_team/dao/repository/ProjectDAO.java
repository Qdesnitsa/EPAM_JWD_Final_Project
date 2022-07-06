package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectDAO {
    List<ProjectDto> findAllForAdmin() throws DAOException;
    List<ProjectDto> findAllByCustomerID(int id) throws DAOException;
    List<ProjectDto> findAllByEmployeeID(int id) throws DAOException;
    Optional<ProjectDto> findByID(int id) throws DAOException;
    boolean add(Project project) throws DAOException;

    boolean update(ProjectDto project) throws DAOException;
}
