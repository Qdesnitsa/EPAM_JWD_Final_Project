package by.sidina.it_team.service.impl;

import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.repository.ProjectCalculationDAO;
import by.sidina.it_team.service.repository.ProjectService;
import by.sidina.it_team.service.exception.ServiceException;

public class ProjectCalculationServiceImpl implements ProjectService.ProjectCalculationService {
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
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean remove(int projectId) throws ServiceException {
        boolean isRemoved;
        try {
            isRemoved = projectCalculationDAO.remove(projectId);
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException(e);
        }
        return isRemoved;
    }
}
