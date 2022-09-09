package by.sidina.it_team.controller.command.impl.admin;

import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
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

public class HomePageForAdminGetCommand implements BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ProjectService projectService = new ProjectServiceImpl(new ProjectDAOImpl());
    private static final int PROJECT_STATUS_DEFAULT = 1;
    private static final int PAGE_SIZE_DEFAULT = 10;
    private static final int PAGE_NUMBER_DEFAULT = 1;

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return user != null
                && user.getRoleId() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if (request.getParameter(ParameterName.PROJECT_STATUS) != null) {
            session.setAttribute(AttributeName.PROJECT_STATUS, request.getParameter(ParameterName.PROJECT_STATUS));
        }
        String projectStatusString = (String) session.getAttribute(AttributeName.PROJECT_STATUS);
        int projectStatus = null == projectStatusString ? PROJECT_STATUS_DEFAULT : Integer.parseInt(projectStatusString);
        int pageSize = PAGE_SIZE_DEFAULT;
        String pageNumberString = (String) request.getAttribute(AttributeName.PAGE_NUMBER);
        int pageNumber = null == pageNumberString ? PAGE_NUMBER_DEFAULT : Integer.parseInt(pageNumberString);
        if (user.getRoleId() == Role.ADMIN.getId()) {
            List<ProjectDto> projects;
            int countProjects;
            try {
                int offset = pageSize * pageNumber - pageSize;
                projects = projectService.findAllForAdmin(pageSize, offset, String.valueOf(projectStatus));
                countProjects = projectService.countAllProjectsForAdmin(projectStatus);
            } catch (ServiceException e) {
                LOGGER.error(e);
                return JSPPagePath.ERROR;
            }
            int pageNumbers = (int) Math.ceil(countProjects / pageSize + 0.5);
            request.setAttribute(AttributeName.PAGE_QUANTITY, pageNumbers);
            request.setAttribute(AttributeName.PAGE_NUMBER, pageNumber);
            request.setAttribute(AttributeName.PAGE_SIZE, pageSize);
            request.setAttribute(AttributeName.PROJECTS, projects);
            request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        }
        return JSPPagePath.ADMIN_ALL_PROJECTS;
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
