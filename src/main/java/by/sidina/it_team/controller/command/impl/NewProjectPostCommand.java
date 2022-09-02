package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.entity.Project;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.ProjectService;
import by.sidina.it_team.service.repository.UserService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.ProjectServiceImpl;
import by.sidina.it_team.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class NewProjectPostCommand implements BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl(new UserDAOImpl());
    private static final ProjectService projectService = new ProjectServiceImpl(new ProjectDAOImpl());
    private static final String EQUALS = "=";
    private static final String DELIMITER_SEMICOLON = "; ";
    private static final String DELIMITER_COMMA = ", ";

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return user != null
                && user.getRole_id() == Role.CUSTOMER.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        String projectName = request.getParameter(ParameterName.PROJECT_NAME);
        if (request.getParameter(ParameterName.START_DATE) == null
                && request.getParameter(ParameterName.END_DATE) == null) {
            return JSPPagePath.CUSTOMER_NEW_PROJECT;
        }
        Date startDate = Date.valueOf(request.getParameter(ParameterName.START_DATE));
        Date endDate = Date.valueOf(request.getParameter(ParameterName.END_DATE));
        if (startDate.compareTo(endDate) <= 0) {
            String[] developers = request.getParameterValues(ParameterName.DEVELOPERS);
            int seniorQuantity = Integer.parseInt(request.getParameter(ParameterName.SENIOR_QUANTITY));
            int middleQuantity = Integer.parseInt(request.getParameter(ParameterName.MIDDLE_QUANTITY));
            int juniorQuantity = Integer.parseInt(request.getParameter(ParameterName.JUNIOR_QUANTITY));
            try {
                Optional<User> userNew = userService.findUserByEmail(user.getEmail());
                int user_id = userNew.get().getId();
                projectService.add(new Project.Builder()
                        .setCustomerID(user_id)
                        .setName(projectName)
                        .setStartDate(startDate)
                        .setEndDate(endDate)
                        .setRequirementComment(new StringBuilder()
                                .append(String.join(DELIMITER_COMMA, developers)).append(DELIMITER_SEMICOLON)
                                .append(ParameterName.SENIOR_QUANTITY).append(EQUALS)
                                .append(seniorQuantity).append(DELIMITER_SEMICOLON)
                                .append(ParameterName.MIDDLE_QUANTITY).append(EQUALS)
                                .append(middleQuantity).append(DELIMITER_SEMICOLON)
                                .append(ParameterName.JUNIOR_QUANTITY).append(EQUALS)
                                .append(juniorQuantity).append(DELIMITER_SEMICOLON)
                                .toString())
                        .build());
            } catch (ServiceException e) {
                LOGGER.error(e);
                return JSPPagePath.ERROR;
            }
        } else {
            request.setAttribute(AttributeName.MESSAGE_START_END_DATE, MSG_START_DATE_AFTER_END_DATE);
        }
        return JSPPagePath.CUSTOMER_NEW_PROJECT;
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
