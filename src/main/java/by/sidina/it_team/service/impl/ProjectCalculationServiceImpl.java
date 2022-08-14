package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.ProjectCalculationDAO;
import by.sidina.it_team.service.repository.ProjectCalculationService;

import by.sidina.it_team.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProjectCalculationServiceImpl implements ProjectCalculationService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ProjectCalculationDAO projectCalculationDAO;

    public ProjectCalculationServiceImpl(ProjectCalculationDAO projectCalculationDAO) {
        this.projectCalculationDAO = projectCalculationDAO;
    }

    @Override
    public boolean add(int projectId) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = projectCalculationDAO.add(projectId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to add project by id from DAO", e);
        }
        return isAdded;
    }

    @Override
    public boolean remove(int projectId) throws ServiceException {
        boolean isRemoved;
        try {
            isRemoved = projectCalculationDAO.remove(projectId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException("Failed to remove project by id from DAO", e);
        }
        return isRemoved;
    }
}
