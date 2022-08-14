package by.sidina.it_team.service.repository;

import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TeamPositionLevelService {
    boolean add(String position, String level, User user, String password) throws ServiceException;

    Optional<EmployeeDto> findByID(int id) throws ServiceException;

    List<EmployeeDto> findAllForAdmin(int limit, int offset) throws ServiceException;

    boolean changePosition(int id, String position) throws ServiceException;

    boolean changeLevel(int id, String level) throws ServiceException;

    int countAllEmployeesForAdmin() throws ServiceException;
}
