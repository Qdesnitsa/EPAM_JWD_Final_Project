package by.sidina.it_team.dao.repository;

import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.TeamSchedule;
import by.sidina.it_team.entity.User;

import java.util.List;

public interface TeamScheduleDAO {
    boolean addEmployeeToProject(TeamSchedule teamSchedule) throws DAOException;
    boolean removeEmployeeFromProject(int id) throws DAOException;
    List<EmployeeDto> findFreeEmployeesForProject(int projectId, String position, Level level, int limit) throws DAOException;
}
