package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.entity.Project;
import by.sidina.it_team.service.repository.ProjectService;
import by.sidina.it_team.service.exception.ServiceException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectDAO projectDAO;
    public ProjectServiceImpl(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }
    @Override
    public List<ProjectDto> findAllForAdmin(int limit, int offset, int status) throws ServiceException {
        List<ProjectDto> projects = new ArrayList<>();
        try {
            projects = projectDAO.findAllForAdmin(limit, offset, status);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return projects;
    }

    @Override
    public List<ProjectDto> findAllByCustomerID(int id) throws ServiceException {
        List<ProjectDto> projects = new ArrayList<>();
        try {
            projects = projectDAO.findAllByCustomerID(id);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return projects;
    }

    @Override
    public List<ProjectDto> findAllByEmployeeID(int id) throws ServiceException {
        List<ProjectDto> projects = new ArrayList<>();
        try {
            projects = projectDAO.findAllByEmployeeID(id);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return projects;
    }

    @Override
    public Optional<ProjectDto> findByID(int id) throws ServiceException {
        Optional<ProjectDto> optional = Optional.empty();
        try {
            optional = projectDAO.findByID(id);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return optional;
    }

    @Override
    public int countAllProjectsForAdmin(int status) throws ServiceException {
        int countProjects = 0;
        try {
            countProjects = projectDAO.countAllProjectsForAdmin(status);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return countProjects;
    }

    @Override
    public boolean add(Project project) throws ServiceException {
        return false;
    }

    @Override
    public boolean changeStatus(int id, int status) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = projectDAO.changeStatus(id, status);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean changeStartDate(int id, Date date) throws ServiceException {
        boolean isChanged;
        try {
            isChanged = projectDAO.changeStartDate(id, date);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isChanged;
    }

    @Override
    public boolean changeEndDate(int id, Date date) throws ServiceException {
        boolean isChanged;
        try {
            isChanged = projectDAO.changeEndDate(id, date);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isChanged;
    }
}
