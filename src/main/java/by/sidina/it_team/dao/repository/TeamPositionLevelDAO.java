package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.TeamPositionLevel;
import by.sidina.it_team.entity.User;

import java.util.List;
import java.util.Optional;

public interface TeamPositionLevelDAO {
    boolean add(int position, int level, User user, String password) throws DAOException;
    Optional<EmployeeDto> findByID(int id) throws DAOException;
    List<EmployeeDto> findAll() throws DAOException;
    boolean changePosition(int id, int position) throws DAOException;
    boolean changeLevel(int id, int level) throws DAOException;
}
