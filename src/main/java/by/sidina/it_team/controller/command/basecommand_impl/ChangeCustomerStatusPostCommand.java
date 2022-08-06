package by.sidina.it_team.controller.command.basecommand_impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.UserService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class ChangeCustomerStatusPostCommand implements BaseCommand {
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
        if (session.getAttribute(ParameterName.CUSTOMER_ID) == null) {
            return JSPPagePath.ADMIN_EDIT_CUSTOMER;
        } else {
            int customerId = Integer.parseInt(String.valueOf(session.getAttribute(AttributeName.CUSTOMER_ID)));
            Optional<CustomerDto> customer = userService.findCustomerByID(customerId);
            if (customer.isPresent()) {
                int status = null == request.getParameter(ParameterName.CHANGE_CUSTOMER_STATUS)
                        ? customer.get().getStatusId()
                        : Integer.parseInt(request.getParameter(ParameterName.CHANGE_CUSTOMER_STATUS));
                boolean isChanged = userService.changeStatus(customerId, status);
                if (isChanged) {
                    customer = userService.findCustomerByID(customerId);
                    request.setAttribute(AttributeName.CUSTOMER, customer.get());
                    request.setAttribute(AttributeName.MESSAGE_SUCCESS, MSG_SUCCESS);
                } else {
                    request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
                }
            } else {
                request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
            }
            return JSPPagePath.ADMIN_EDIT_CUSTOMER;
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