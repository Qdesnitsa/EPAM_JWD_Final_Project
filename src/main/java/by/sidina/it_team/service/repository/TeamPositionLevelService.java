package by.sidina.it_team.service.repository;

import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TeamPositionLevelService {
    boolean add(int position, int level, User user, String password) throws ServiceException;

    Optional<EmployeeDto> findByID(int id) throws ServiceException;

    List<EmployeeDto> findAllForAdmin(int limit, int offset) throws ServiceException;

    boolean changePosition(int id, int position) throws ServiceException;

    boolean changeLevel(int id, int level) throws ServiceException;

    int countAllEmployeesForAdmin() throws ServiceException;
}
