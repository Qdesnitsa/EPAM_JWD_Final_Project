package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.ParameterName;
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

public class AddEmployeeOnProjectPostCommand extends BaseCommand {
    private final String MSG_SUCCESS = "Successfully";
    private final String MSG_FAIL = "Operation failed";
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
        if (session.getAttribute(ParameterName.PROJECT_ID) == null) {
            return JSPPagePath.ADMIN_EDIT_PROJECT;
        } else {
            int projectId = Integer.parseInt(String.valueOf(session.getAttribute(ParameterName.PROJECT_ID)));
            ProjectDAO projectDAO = new ProjectDAOImpl();
            Optional<ProjectDto> project = projectDAO.findByID(projectId);
            if (project.isPresent()) {
                TeamScheduleDAO teamScheduleDAO = new TeamScheduleDAOImpl();
                int idToAdd = Integer.parseInt(request.getParameter(ParameterName.ADD_ID));
                boolean isAdded = teamScheduleDAO.addEmployeeToProject(idToAdd, projectId);
                if (isAdded) {
                    request.setAttribute(AttributeName.MESSAGE, MSG_SUCCESS);
                    project = projectDAO.findByID(projectId);
                    request.setAttribute(AttributeName.PROJECT, project.get());
                    String position = (String) session.getAttribute("employee_position");
                    Level level = Level.valueOf(String.valueOf(session.getAttribute("level")).toUpperCase());
                    int quantity = (int) session.getAttribute("quantity");
                    List<EmployeeDto> freeEmployees = teamScheduleDAO.findFreeEmployeesForProject(projectId, position, level, quantity);
                    request.setAttribute(AttributeName.FREE_EMPLOYEES, freeEmployees);
                    return JSPPagePath.ADMIN_EDIT_PROJECT;
                } else {
                    request.setAttribute(AttributeName.MESSAGE, MSG_FAIL);
                }
            } else {
                request.setAttribute(AttributeName.MESSAGE, NO_SUCH_PROJECT_ID);
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
