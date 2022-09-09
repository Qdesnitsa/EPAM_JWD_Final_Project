package by.sidina.it_team.controller.command.impl.admin;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class ShowCustomersGetCommand implements BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String SEARCH_PATTERN_DEFAULT = "";
    public static final int PAGE_NUMBER_DEFAULT = 1;
    public static final int PAGE_SIZE_DEFAULT = 10;
    private static final UserService userService = new UserServiceImpl(new UserDAOImpl());

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return user != null
                && user.getRoleId() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
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
        String userPattern;
        if (request.getParameter(ParameterName.SEARCH_PATTERN) != null) {
            session.setAttribute(AttributeName.SEARCH_PATTERN, request.getParameter(ParameterName.SEARCH_PATTERN));
            userPattern = (String) session.getAttribute(AttributeName.SEARCH_PATTERN);
        } else if (session.getAttribute(AttributeName.SEARCH_PATTERN) != null) {
            userPattern = (String) session.getAttribute(AttributeName.SEARCH_PATTERN);
        } else {
            userPattern = SEARCH_PATTERN_DEFAULT;
        }
        try {
            List<CustomerDto> customers = userService.findAllCustomersByPattern(pageSize, offset, userPattern);
            countCustomers = userService.countAllCustomersForAdmin(userPattern);
            int pageNumbers = (int) Math.ceil(countCustomers / pageSize + 0.5);
            request.setAttribute(AttributeName.PAGE_QUANTITY, pageNumbers);
            request.setAttribute(AttributeName.PAGE_NUMBER, pageNumber);
            request.setAttribute(AttributeName.PAGE_SIZE, pageSize);
            request.setAttribute(AttributeName.CUSTOMERS, customers);
        } catch (ServiceException e) {
            LOGGER.error(e);
            return JSPPagePath.ERROR;
        }
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
