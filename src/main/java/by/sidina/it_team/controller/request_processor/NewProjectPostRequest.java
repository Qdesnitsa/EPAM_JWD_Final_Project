package by.sidina.it_team.controller.request_processor;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.ParameterName;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.entity.Project;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

public class NewProjectPostRequest extends BaseProcessor {
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
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        String projectName = request.getParameter(ParameterName.PROJECT_NAME);
        Date startDate = Date.valueOf(request.getParameter(ParameterName.START_DATE));
        Date endDate = Date.valueOf(request.getParameter(ParameterName.END_DATE));
        String[] developers = request.getParameterValues(ParameterName.DEVELOPERS);
        int seniorQuantity = Integer.parseInt(request.getParameter(ParameterName.SENIOR_QUANTITY));
        int middleQuantity = Integer.parseInt(request.getParameter(ParameterName.MIDDLE_QUANTITY));
        int juniorQuantity = Integer.parseInt(request.getParameter(ParameterName.JUNIOR_QUANTITY));
        int user_id = user.getId();

        ProjectDAOImpl projectDAO = new ProjectDAOImpl();
        projectDAO.add(new Project.Builder()
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

        return HomePageByRoleProvider.getProjectsPageForUser(user, request);
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
