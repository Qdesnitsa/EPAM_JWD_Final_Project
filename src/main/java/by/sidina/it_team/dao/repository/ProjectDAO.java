package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.Project;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ProjectDAO {
    List<ProjectDto> findAllForAdmin(int limit, int offset) throws DAOException;
    List<ProjectDto> findAllByCustomerID(int id) throws DAOException;
    List<ProjectDto> findAllByEmployeeID(int id) throws DAOException;
    Optional<ProjectDto> findByID(int id) throws DAOException;
    int countAllForAdmin() throws DAOException;
    boolean add(Project project) throws DAOException;

    boolean changeStatus(int id, int status) throws DAOException;
    boolean changeStartDate(int id, Date date) throws DAOException;
    boolean changeEndDate(int id, Date date) throws DAOException;
}
