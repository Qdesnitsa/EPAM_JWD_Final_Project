package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.entity.Project;
import by.sidina.it_team.service.repository.ProjectService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ProjectDAO projectDAO;

    public ProjectServiceImpl(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @Override
    public List<ProjectDto> findAllForAdmin(int limit, int offset, String status) throws ServiceException {
        List<ProjectDto> projects = new ArrayList<>();
        if (Validator.isValidProjectStatus(status)) {
            try {
                projects = projectDAO.findAllForAdmin(limit, offset, Integer.parseInt(status));
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to find all projects from DAO", e);
            }
        }
        return projects;
    }

    @Override
    public List<ProjectDto> findAllByCustomerID(int id) throws ServiceException {
        List<ProjectDto> projects;
        try {
            projects = projectDAO.findAllByCustomerID(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find all projects by customer ID from DAO", e);
        }
        return projects;
    }

    @Override
    public List<ProjectDto> findAllByEmployeeID(int id) throws ServiceException {
        List<ProjectDto> projects;
        try {
            projects = projectDAO.findAllByEmployeeID(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find all projects by employee ID from DAO", e);
        }
        return projects;
    }

    @Override
    public Optional<ProjectDto> findByID(int id) throws ServiceException {
        Optional<ProjectDto> optional = Optional.empty();
        try {
            optional = projectDAO.findByID(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to find project by ID from DAO", e);
        }
        return optional;
    }

    @Override
    public int countAllProjectsForAdmin(int status) throws ServiceException {
        int countProjects = 0;
        try {
            countProjects = projectDAO.countAllProjectsForAdmin(status);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to count all projects from DAO", e);
        }
        return countProjects;
    }

    @Override
    public boolean add(Project project) throws ServiceException {
        boolean isAdded = false;
        if (project != null) {
            try {
                isAdded = projectDAO.add(project);
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to add project from DAO", e);
            }
        }
        return isAdded;
    }

    @Override
    public boolean changeStatus(int id, String status) throws ServiceException {
        boolean isAdded = false;
        if (Validator.isValidProjectStatus(status)) {
            try {
                isAdded = projectDAO.changeStatus(id, Integer.parseInt(status));
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to change project status from DAO", e);
            }
        }
        return isAdded;
    }

    @Override
    public boolean changeStartDate(int id, String date) throws ServiceException {
        boolean isChanged = false;
        if (Validator.isValidDate(date)) {
            try {
                isChanged = projectDAO.changeStartDate(id, Date.valueOf(date));
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to change project start date from DAO", e);
            }
        }
        return isChanged;
    }

    @Override
    public boolean changeEndDate(int id, String date) throws ServiceException {
        boolean isChanged = false;
        if (Validator.isValidDate(date)) {
            try {
                isChanged = projectDAO.changeEndDate(id, Date.valueOf(date));
            } catch (DAOException e) {
                LOGGER.error(e);
                throw new ServiceException("Failed to change project end date from DAO", e);
            }
        }
        return isChanged;
    }
}
