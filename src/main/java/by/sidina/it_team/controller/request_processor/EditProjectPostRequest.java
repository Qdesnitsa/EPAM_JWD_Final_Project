package by.sidina.it_team.controller.request_processor;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectCalculationDAOImpl;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.TeamScheduleDAOImpl;
import by.sidina.it_team.dao.repository.ProjectCalculationDAO;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.dao.repository.TeamScheduleDAO;
import by.sidina.it_team.entity.Level;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EditProjectPostRequest extends BaseProcessor {
    private final String MSG_SUCCESS = "Successfully";
    private final String MSG_FAIL = "Operation failed";

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return user != null && user.getRole_id() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
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
                request.setAttribute(AttributeName.PROJECT, project.get());
                return JSPPagePath.ADMIN_EDIT_PROJECT;
            } else if (request.getParameter("show_employees") != null) {
                ProjectDAO projectDAO = new ProjectDAOImpl();
                Optional<ProjectDto> project = projectDAO.findByID(projectId);
                request.setAttribute(AttributeName.PROJECT, project.get());
                TeamScheduleDAO teamScheduleDAO = new TeamScheduleDAOImpl();
                String position = request.getParameter("employee_position");
                Level level = Level.valueOf(request.getParameter("level").toUpperCase());
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                List<EmployeeDto> employees = teamScheduleDAO.findFreeEmployeesForProject(projectId, position, level, quantity);
                request.setAttribute(AttributeName.EMPLOYEES, employees);
                return JSPPagePath.ADMIN_EDIT_PROJECT;
            } else if (request.getParameter("change_status") != null) {
                ProjectDAO projectDAO = new ProjectDAOImpl();
                int status = Integer.parseInt(request.getParameter("project_status"));
                boolean isChanged = projectDAO.changeStatus(projectId, status);
                if (isChanged) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            } else if (request.getParameter("change_start") != null) {
                ProjectDAO projectDAO = new ProjectDAOImpl();
                Date startDate = Date.valueOf(request.getParameter("start_date"));
                boolean isChanged = projectDAO.changeStartDate(projectId, startDate);
                if (isChanged) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            } else if (request.getParameter("change_end") != null) {
                ProjectDAO projectDAO = new ProjectDAOImpl();
                Date endDate = Date.valueOf(request.getParameter("end_date"));
                boolean isChanged = projectDAO.changeEndDate(projectId, endDate);
                if (isChanged) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            } else if (request.getParameter("add_employee") != null) {
                TeamScheduleDAO teamScheduleDAO = new TeamScheduleDAOImpl();
                int idToAdd = Integer.parseInt(request.getParameter("add_id"));
                boolean isAdded = teamScheduleDAO.addEmployeeToProject(idToAdd, projectId);
                if (isAdded) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            } else if (request.getParameter("remove_employee") != null) {
                TeamScheduleDAO teamScheduleDAO = new TeamScheduleDAOImpl();
                int idToAdd = Integer.parseInt(request.getParameter("remove_id"));
                boolean isAdded = teamScheduleDAO.removeEmployeeFromProject(idToAdd, projectId);
                if (isAdded) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            } else if (request.getParameter("calculate_project") != null) {
                ProjectCalculationDAO projectCalculation = new ProjectCalculationDAOImpl();
                boolean isAdded = projectCalculation.add(projectId);
                if (isAdded) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            }
            return JSPPagePath.ADMIN_EDIT_PROJECT;
        }
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
