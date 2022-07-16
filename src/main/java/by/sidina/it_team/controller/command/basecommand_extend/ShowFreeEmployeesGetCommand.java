package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.TeamScheduleDAOImpl;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.dao.repository.TeamScheduleDAO;
import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ShowFreeEmployeesGetCommand extends BaseCommand {
    private static final String NO_SUCH_PROJECT_ID = "Project with this ID does not exist.";
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return user != null && user.getRole_id() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if ("" == request.getParameter("project_id") || "0".equals(request.getParameter("project_id"))) {
            return JSPPagePath.ADMIN_EDIT_PROJECT;
        } else {
            if ("0".equals(request.getParameter("quantity"))) {
                int projectId = Integer.parseInt(request.getParameter("project_id"));
                ProjectDAO projectDAO = new ProjectDAOImpl();
                Optional<ProjectDto> project = projectDAO.findByID(projectId);
                TeamScheduleDAO teamScheduleDAO = new TeamScheduleDAOImpl();
                List<EmployeeDto> employees = teamScheduleDAO.findEmployeesOnProject(projectId);
                if (project.isPresent()) {
                    request.setAttribute(AttributeName.PROJECT, project.get());
                    request.setAttribute(AttributeName.EMPLOYEES, employees);
                    return JSPPagePath.ADMIN_EDIT_PROJECT;
                } else {
                    request.setAttribute("message", NO_SUCH_PROJECT_ID);
                    return JSPPagePath.ADMIN_EDIT_PROJECT;
                }
            } else {
                int projectId = Integer.parseInt(request.getParameter("project_id"));
                ProjectDAO projectDAO = new ProjectDAOImpl();
                Optional<ProjectDto> project = projectDAO.findByID(projectId);
                TeamScheduleDAO teamScheduleDAO = new TeamScheduleDAOImpl();
                String position = request.getParameter("employee_position");
                Level level = Level.valueOf(request.getParameter("level").toUpperCase());
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                List<EmployeeDto> employees = teamScheduleDAO.findFreeEmployeesForProject(projectId, position, level, quantity);
                if (project.isPresent()) {
                    request.setAttribute(AttributeName.PROJECT, project.get());
                    request.setAttribute(AttributeName.EMPLOYEES, employees);
                    return JSPPagePath.ADMIN_EDIT_PROJECT;
                } else {
                    request.setAttribute("message", NO_SUCH_PROJECT_ID);
                    return JSPPagePath.ADMIN_EDIT_PROJECT;
                }
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
