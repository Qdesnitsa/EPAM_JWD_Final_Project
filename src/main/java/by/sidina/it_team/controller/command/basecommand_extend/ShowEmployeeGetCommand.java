package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class ShowEmployeeGetCommand extends BaseCommand {
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
        if (request.getParameter(ParameterName.EMPLOYEE_ID) == null ||
                request.getParameter(ParameterName.EMPLOYEE_ID).isEmpty()) {
            return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
        } else {
            int employeeId = Integer.parseInt(request.getParameter(ParameterName.EMPLOYEE_ID));
            TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
            Optional<EmployeeDto> employee = teamPositionLevelDAO.findByID(employeeId);
            if (employee.isPresent()) {
                request.setAttribute(AttributeName.EMPLOYEE, employee.get());
                request.setAttribute(AttributeName.MESSAGE_SUCCESS, MSG_SUCCESS);
                return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
            } else {
                request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
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
