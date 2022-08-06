package by.sidina.it_team.controller.command.basecommand_impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.TeamPositionLevelService;
import by.sidina.it_team.service.UserService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.TeamPositionLevelServiceImpl;
import by.sidina.it_team.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class ChangeEmployeeStatusPostCommand implements BaseCommand {
    private static final TeamPositionLevelService teamPositionLevelService
            = new TeamPositionLevelServiceImpl(new TeamPositionLevelDAOImpl());
    private static final UserService userService = new UserServiceImpl(new UserDAOImpl());

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
        if (session.getAttribute(ParameterName.EMPLOYEE_ID) == null) {
            return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
        } else {
            int employeeId = Integer.parseInt(String.valueOf(session.getAttribute(AttributeName.EMPLOYEE_ID)));
            Optional<EmployeeDto> employee = teamPositionLevelService.findByID(employeeId);
            if (employee.isPresent()) {
                int status = null == request.getParameter(ParameterName.CHANGE_EMPLOYEE_STATUS)
                        ? employee.get().getStatusId()
                        : Integer.parseInt(request.getParameter(ParameterName.CHANGE_EMPLOYEE_STATUS));
                boolean isChanged = userService.changeStatus(employeeId, status);
                if (isChanged) {
                    employee = teamPositionLevelService.findByID(employeeId);
                    request.setAttribute(AttributeName.EMPLOYEE, employee.get());
                    request.setAttribute(AttributeName.MESSAGE_SUCCESS, MSG_SUCCESS);
                } else {
                    request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
                }
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
