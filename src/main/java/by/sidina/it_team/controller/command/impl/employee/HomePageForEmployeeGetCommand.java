package by.sidina.it_team.controller.command.impl.employee;

import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.ProjectServiceImpl;
import by.sidina.it_team.service.repository.ProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class HomePageForEmployeeGetCommand implements BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ProjectService projectService = new ProjectServiceImpl(new ProjectDAOImpl());

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return user != null
                && user.getRoleId() == Role.EMPLOYEE.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        LocalDate currentDate = LocalDate.now();
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        if (user.getRoleId() == Role.EMPLOYEE.getId()) {
            List<ProjectDto> projects;
            try {
                projects = projectService.findAllByEmployeeID(user.getId());
            } catch (ServiceException e) {
                LOGGER.error(e);
                return JSPPagePath.ERROR;
            }
            request.setAttribute(AttributeName.PROJECTS, projects);
            request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        }
        return JSPPagePath.EMPLOYEE_PROJECTS;
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
