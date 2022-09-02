package by.sidina.it_team.controller.command;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.ProjectServiceImpl;
import by.sidina.it_team.service.impl.UserServiceImpl;
import by.sidina.it_team.service.repository.ProjectService;
import by.sidina.it_team.service.repository.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HomePageByRoleProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ProjectService projectService = new ProjectServiceImpl(new ProjectDAOImpl());
    private static final UserService userService = new UserServiceImpl(new UserDAOImpl());
    private static final int PROJECT_STATUS_DEFAULT = 1;
    private static final int PAGE_SIZE_DEFAULT = 10;
    private static final int PAGE_NUMBER_DEFAULT = 1;

    public static String getProjectsPageForUser(User user, HttpServletRequest request) {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        String projectStatusString = (String) request.getSession().getAttribute(AttributeName.PROJECT_STATUS);
        int projectStatus = null == projectStatusString ? PROJECT_STATUS_DEFAULT : Integer.parseInt(projectStatusString);
        int pageSize = PAGE_SIZE_DEFAULT;
        String pageNumberString = (String) request.getAttribute(AttributeName.PAGE_NUMBER);
        int pageNumber = null == pageNumberString ? PAGE_NUMBER_DEFAULT : Integer.parseInt(pageNumberString);
        if (user.getRole_id() == Role.ADMIN.getId()) {
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
            return JSPPagePath.ADMIN_ALL_PROJECTS;
        } else if (user.getRole_id() == Role.EMPLOYEE.getId()) {
            List<ProjectDto> projects;
            try {
                projects = projectService.findAllByEmployeeID(user.getId());
            } catch (ServiceException e) {
                LOGGER.error(e);
                return JSPPagePath.ERROR;
            }
            request.setAttribute(AttributeName.PROJECTS, projects);
            request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
            return JSPPagePath.EMPLOYEE_PROJECTS;
        } else if (user.getRole_id() == Role.CUSTOMER.getId()) {
            Optional<User> existingUser;
            try {
                existingUser = userService.findUserByEmail(user.getEmail());
            } catch (ServiceException e) {
                LOGGER.error(e);
                return JSPPagePath.ERROR;
            }
            List<ProjectDto> projects;
            try {
                projects = projectService.findAllByCustomerID(existingUser.get().getId());
            } catch (ServiceException e) {
                LOGGER.error(e);
                return JSPPagePath.ERROR;
            }
            request.setAttribute(AttributeName.PROJECTS, projects);
            request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        }
        return JSPPagePath.CUSTOMER_PROJECTS;
    }
}
