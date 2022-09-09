package by.sidina.it_team.controller.command.impl.admin;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.TeamScheduleDAOImpl;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.ProjectService;
import by.sidina.it_team.service.repository.TeamScheduleService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.ProjectServiceImpl;
import by.sidina.it_team.service.impl.TeamScheduleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class RemoveEmployeeFromProjectPostCommand implements BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ProjectService projectService = new ProjectServiceImpl(new ProjectDAOImpl());
    private static final TeamScheduleService teamScheduleService = new TeamScheduleServiceImpl(new TeamScheduleDAOImpl());

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return user != null && user.getRoleId() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if (session.getAttribute(ParameterName.PROJECT_ID) == null ||
                request.getParameter(ParameterName.REMOVE_ID) == null) {
            return JSPPagePath.ADMIN_EDIT_PROJECT;
        } else {
            int projectId = Integer.parseInt(String.valueOf(session.getAttribute(ParameterName.PROJECT_ID)));
            try {
                Optional<ProjectDto> project = projectService.findByID(projectId);
                if (project.isPresent()) {
                    int idToRemove = Integer.parseInt(request.getParameter(ParameterName.REMOVE_ID));
                    boolean isRemoved = teamScheduleService.removeEmployeeFromProject(idToRemove, projectId);
                    if (isRemoved) {
                        request.setAttribute(AttributeName.MESSAGE_SUCCESS, MSG_SUCCESS);
                    }
                    project = projectService.findByID(projectId);
                    request.setAttribute(AttributeName.PROJECT, project.get());
                    List<EmployeeDto> employees = teamScheduleService.findEmployeesOnProject(projectId);
                    request.setAttribute(AttributeName.EMPLOYEES, employees);
                    return JSPPagePath.ADMIN_EDIT_PROJECT;
                } else {
                    request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
                }
            } catch (ServiceException e) {
                LOGGER.error(e);
                return JSPPagePath.ERROR;
            }
        }
        return JSPPagePath.ADMIN_EDIT_PROJECT;
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
