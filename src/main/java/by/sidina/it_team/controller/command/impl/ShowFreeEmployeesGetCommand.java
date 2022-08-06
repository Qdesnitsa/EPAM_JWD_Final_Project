package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.TeamScheduleDAOImpl;
import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.ProjectService;
import by.sidina.it_team.service.repository.TeamScheduleService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.ProjectServiceImpl;
import by.sidina.it_team.service.impl.TeamScheduleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class ShowFreeEmployeesGetCommand implements BaseCommand {
    public static final String EMPLOYEE_LEVEL_DEFAULT = "junior";
    public static final String REQUIRED_QUANTITY_EMPLOYEES_DEFAULT = "0";
    private static final ProjectService projectService = new ProjectServiceImpl(new ProjectDAOImpl());
    private static final TeamScheduleService teamScheduleService = new TeamScheduleServiceImpl(new TeamScheduleDAOImpl());

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return user != null && user.getRole_id() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if (session.getAttribute(AttributeName.PROJECT_ID) == null) {
            return JSPPagePath.ADMIN_EDIT_PROJECT;
        } else {
            int projectId = Integer.parseInt(String.valueOf(session.getAttribute(AttributeName.PROJECT_ID)));
            Optional<ProjectDto> project = projectService.findByID(projectId);
            String position = request.getParameter(ParameterName.EMPLOYEE_POSITION);
            String levelString = request.getParameter(ParameterName.LEVEL);
            levelString = null == levelString
                    ? EMPLOYEE_LEVEL_DEFAULT
                    : levelString;
            Level employeeLevel = Level.valueOf(levelString.toUpperCase());
            String requiredQuantityOfEmployeesString = request.getParameter(ParameterName.QUANTITY);
            requiredQuantityOfEmployeesString = null == requiredQuantityOfEmployeesString
                    ? REQUIRED_QUANTITY_EMPLOYEES_DEFAULT
                    : requiredQuantityOfEmployeesString;
            int quantity = Integer.parseInt(requiredQuantityOfEmployeesString);
            List<EmployeeDto> freeEmployees = teamScheduleService.findFreeEmployeesForProject(projectId, position, employeeLevel, quantity);
            if (project.isPresent()) {
                session.setAttribute(AttributeName.QUANTITY, quantity);
                session.setAttribute(AttributeName.EMPLOYEE_POSITION, position);
                session.setAttribute(AttributeName.EMPLOYEE_LEVEL, employeeLevel);
                request.setAttribute(AttributeName.PROJECT, project.get());
                request.setAttribute(AttributeName.FREE_EMPLOYEES, freeEmployees);
                return JSPPagePath.ADMIN_EDIT_PROJECT;
            } else {
                request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
                return JSPPagePath.ADMIN_EDIT_PROJECT;
            }
        }
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        if (user == null) {
            return JSPPagePath.SIGN_IN;
        } else {
            return JSPPagePath.ERROR;
        }
    }
}
