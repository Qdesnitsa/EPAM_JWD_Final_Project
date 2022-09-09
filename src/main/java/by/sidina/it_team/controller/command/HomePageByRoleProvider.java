package by.sidina.it_team.controller.command;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.impl.admin.HomePageForAdminGetCommand;
import by.sidina.it_team.controller.command.impl.customer.HomePageForCustomerGetCommand;
import by.sidina.it_team.controller.command.impl.employee.HomePageForEmployeeGetCommand;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HomePageByRoleProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private Map<Integer, BaseCommand> roleMap;
    private static HomePageByRoleProvider homePageByRoleProvider = new HomePageByRoleProvider();

    public HomePageByRoleProvider() {
        roleMap = new HashMap<>();
        roleMap.put(1, new HomePageForAdminGetCommand());
        roleMap.put(2, new HomePageForEmployeeGetCommand());
        roleMap.put(3, new HomePageForCustomerGetCommand());
    }

    public BaseCommand getHomePage(int roleId) {
        return roleMap.get(roleId);
    }

    public static String getProjectsPageForUser(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        int userRoleId = user.getRoleId();
        try {
            return homePageByRoleProvider.getHomePage(userRoleId).getExpectedJspPage(request, response);
        } catch (ServiceException | IOException e) {
            LOGGER.error(e);
            return JSPPagePath.ERROR;
        }
    }
}
