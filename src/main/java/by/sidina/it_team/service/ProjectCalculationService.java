package by.sidina.it_team.service;

import by.sidina.it_team.service.exception.ServiceException;

public interface ProjectCalculationService {
    boolean add(int projectId) throws ServiceException;
    boolean remove(int projectId) throws ServiceException;
}
