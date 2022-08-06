package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.UserService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class ShowCustomersGetCommand implements BaseCommand {
    public static final int PAGE_NUMBER_DEFAULT = 1;
    public static final int PAGE_SIZE_DEFAULT = 5;
    private static final UserService userService = new UserServiceImpl(new UserDAOImpl());

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return user != null
                && user.getRole_id() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        int pageSize = PAGE_SIZE_DEFAULT;
        String pageNumberString = request.getParameter(ParameterName.PAGE_NUMBER);
        int pageNumber = null == pageNumberString
                ? PAGE_NUMBER_DEFAULT
                : Integer.parseInt(pageNumberString);
        int countCustomers;
        int offset = pageSize * pageNumber - pageSize;
        List<CustomerDto> customers = userService.findAllCustomers(pageSize, offset);
        countCustomers = userService.countAllCustomersForAdmin();
        int pageNumbers = (int) Math.ceil(countCustomers / pageSize + 0.5);
        request.setAttribute(AttributeName.PAGE_QUANTITY, pageNumbers);
        request.setAttribute(AttributeName.PAGE_NUMBER, pageNumber);
        request.setAttribute(AttributeName.PAGE_SIZE, pageSize);
        request.setAttribute(AttributeName.CUSTOMERS, customers);
        return JSPPagePath.ADMIN_ALL_CUSTOMERS;
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