package by.sidina.it_team.controller.request_processor;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.impl.TeamScheduleDAOImpl;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.dao.repository.TeamScheduleDAO;
import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditProjectGetRequest extends BaseProcessor{
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return user != null && user.getRole_id() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if ("" == request.getParameter("project_id") || null == request.getParameter("project_id")) {
            return JSPPagePath.ADMIN_EDIT_PROJECT;
        } else {
            int projectId = Integer.parseInt(request.getParameter("project_id"));
            if (request.getParameter("show_project") != null) {

                ProjectDAO projectDAO = new ProjectDAOImpl();
                Optional<ProjectDto> project = projectDAO.findByID(projectId);
                request.setAttribute(AttributeName.PROJECT, project);
                return JSPPagePath.ADMIN_EDIT_PROJECT;
            } else if (request.getParameter("show_employees") != null) {
                TeamScheduleDAO teamScheduleDAO = new TeamScheduleDAOImpl();
                String position = request.getParameter("employee_position");
                Level level = Level.valueOf(request.getParameter("level").toUpperCase());
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                List<EmployeeDto> employees = teamScheduleDAO.findFreeEmployeesForProject(projectId, position, level, quantity);
                request.setAttribute(AttributeName.EMPLOYEES, employees);

                return JSPPagePath.ADMIN_EDIT_PROJECT;
            }
        }return JSPPagePath.ADMIN_EDIT_PROJECT;
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        if (user == null) {
            return JSPPagePath.SIGN_IN;
        } else {
            return JSPPagePath.ERROR;
        }
    }
}
