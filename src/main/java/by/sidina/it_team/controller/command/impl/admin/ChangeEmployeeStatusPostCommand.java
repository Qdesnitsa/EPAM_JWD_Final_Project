package by.sidina.it_team.controller.command.impl.admin;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.TeamPositionLevelService;
import by.sidina.it_team.service.repository.UserService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.TeamPositionLevelServiceImpl;
import by.sidina.it_team.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class ChangeEmployeeStatusPostCommand implements BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TeamPositionLevelService teamPositionLevelService
            = new TeamPositionLevelServiceImpl(new TeamPositionLevelDAOImpl());
    private static final UserService userService = new UserServiceImpl(new UserDAOImpl());

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
        int employeeId;
        if (request.getParameter(ParameterName.EMPLOYEE_ID) == null) {
            employeeId = Integer.parseInt(String.valueOf(session.getAttribute(ParameterName.EMPLOYEE_ID)));
        } else {
            employeeId = Integer.parseInt(String.valueOf(request.getParameter(ParameterName.EMPLOYEE_ID)));
            session.setAttribute(AttributeName.EMPLOYEE_ID, request.getParameter(ParameterName.EMPLOYEE_ID));
        }
        try {
            Optional<EmployeeDto> employee = teamPositionLevelService.findByID(employeeId);
            if (employee.isPresent()) {
                String status = request.getParameter(ParameterName.CHANGE_EMPLOYEE_STATUS);
                boolean isChanged = userService.changeStatus(employeeId, status);
                if (isChanged) {
                    request.setAttribute(AttributeName.MESSAGE_SUCCESS, MSG_SUCCESS);
                }
                employee = teamPositionLevelService.findByID(employeeId);
                request.setAttribute(AttributeName.EMPLOYEE, employee.get());
                return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
            } else {
                request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            return JSPPagePath.ERROR;
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
