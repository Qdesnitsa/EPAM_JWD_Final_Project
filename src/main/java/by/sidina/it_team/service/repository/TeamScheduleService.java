package by.sidina.it_team.service.repository;

import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.TeamSchedule;
import by.sidina.it_team.service.exception.ServiceException;

import java.util.List;

public interface TeamScheduleService {
    boolean addEmployeeToProject(int employeeId, int projectId) throws ServiceException;

    boolean removeEmployeeFromProject(int employeeId, int projectId) throws ServiceException;

    List<EmployeeDto> findEmployeesOnProject(int projectId) throws ServiceException;

    List<EmployeeDto> findFreeEmployeesForProject(int projectId, String position, Level level, int limit) throws ServiceException;

    boolean addHoursByEmployeeId(TeamSchedule teamSchedule) throws ServiceException;
}
