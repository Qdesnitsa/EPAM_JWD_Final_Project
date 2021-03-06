package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

public class ChangeEmployeePositionPostCommand extends BaseCommand {
    private final String MSG_SUCCESS = "Successfully";
    private final String MSG_FAIL = "Operation failed";
    private static final String NO_SUCH_EMPLOYEE_ID = "Employee with this ID does not exist.";

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
        if ("" == request.getParameter("employee_id") || "0".equals(request.getParameter("employee_id"))) {
            return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
        } else {
            int employeeId = Integer.parseInt(request.getParameter("employee_id"));
            TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
            Optional<EmployeeDto> employee = teamPositionLevelDAO.findByID(employeeId);
            if (employee.isPresent()) {
                int position = Integer.parseInt(request.getParameter("employee_position"));
                boolean isChanged = teamPositionLevelDAO.changePosition(employeeId, position);
                if (isChanged) {
                    employee = teamPositionLevelDAO.findByID(employeeId);
                    request.setAttribute(AttributeName.EMPLOYEE, employee.get());
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
                return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
            } else {
                request.setAttribute("message", NO_SUCH_EMPLOYEE_ID);
            }
        }
        return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
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
