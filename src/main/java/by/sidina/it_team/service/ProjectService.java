package by.sidina.it_team.service;

import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.Project;
import by.sidina.it_team.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<ProjectDto> findAllForAdmin(int limit, int offset, int status) throws ServiceException;

    List<ProjectDto> findAllByCustomerID(int id) throws ServiceException;

    List<ProjectDto> findAllByEmployeeID(int id) throws ServiceException;

    Optional<ProjectDto> findByID(int id) throws ServiceException;

    int countAllProjectsForAdmin(int status) throws ServiceException;

    boolean add(Project project) throws ServiceException;

    boolean changeStatus(int id, int status) throws ServiceException;

    boolean changeStartDate(int id, Date date) throws ServiceException;

    boolean changeEndDate(int id, Date date) throws ServiceException;
}
