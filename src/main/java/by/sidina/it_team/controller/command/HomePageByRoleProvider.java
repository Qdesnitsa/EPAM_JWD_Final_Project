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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HomePageByRoleProvider {
    private static final int PROJECT_STATUS_DEFAULT = 1;
    private static final int PAGE_SIZE_DEFAULT = 5;
    private static final int PAGE_NUMBER_DEFAULT = 1;

    public static String getProjectsPageForUser(User user, HttpServletRequest request) {
        LocalDate currentDate = LocalDate.now();
        ProjectDAO projectDAO = new ProjectDAOImpl();
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        String projectStatusString = (String) request.getAttribute(AttributeName.PROJECT_STATUS);
        int projectStatus = null == projectStatusString ? PROJECT_STATUS_DEFAULT : Integer.parseInt(projectStatusString);
        int pageSize = PAGE_SIZE_DEFAULT;
        String pageNumberString = (String) request.getAttribute(AttributeName.PAGE_NUMBER);
        int pageNumber = null == pageNumberString ? PAGE_NUMBER_DEFAULT : Integer.parseInt(pageNumberString);
        if (user.getRole_id() == Role.ADMIN.getId()) {
            List<ProjectDto> projects = null;
            int countProjects;
            try {
                int offset = pageSize * pageNumber - pageSize;
                projects = projectDAO.findAllForAdmin(pageSize, offset, projectStatus);
                countProjects = projectDAO.countAllProjectsForAdmin(projectStatus);
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
            int pageNumbers = (int) Math.ceil(countProjects / pageSize + 0.5);
            request.setAttribute(AttributeName.PAGE_QUANTITY, pageNumbers);
            request.setAttribute(AttributeName.PAGE_NUMBER, pageNumber);
            request.setAttribute(AttributeName.PAGE_SIZE, pageSize);
            request.setAttribute(AttributeName.PROJECTS, projects);
            request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
            return JSPPagePath.ADMIN_ALL_PROJECTS;
        } else if (user.getRole_id() == Role.EMPLOYEE.getId()) {
            List<ProjectDto> projects = null;
            try {
                projects = projectDAO.findAllByEmployeeID(user.getId());
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute(AttributeName.PROJECTS, projects);
            request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
            return JSPPagePath.EMPLOYEE_PROJECTS;
        } else if (user.getRole_id() == Role.CUSTOMER.getId()) {
            UserDAOImpl userDaoImpl = new UserDAOImpl();
            Optional<User> existingUser = null;
            try {
                existingUser = userDaoImpl.findUserByEmail(user.getEmail());
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
            List<ProjectDto> projects = null;
            try {
                projects = projectDAO.findAllByCustomerID(existingUser.get().getId());
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute(AttributeName.PROJECTS, projects);
            request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
            return JSPPagePath.CUSTOMER_PROJECTS;
        }
        try {
            throw new DAOException("Unknown role");
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
}
