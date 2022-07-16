package by.sidina.it_team.controller.command;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
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
    public static String getProjectsPageForUser(User user, HttpServletRequest request) {
        LocalDate currentDate = LocalDate.now();
        ProjectDAO projectDAO = new ProjectDAOImpl();
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if(user.getRole_id() ==  Role.ADMIN.getId()) {
            List<ProjectDto> projects = null;
            try {
                projects = projectDAO.findAllForAdmin();
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute(AttributeName.PROJECTS, projects);
            request.setAttribute(AttributeName.CURRENT_DATE,currentDate);
            return JSPPagePath.ADMIN_ALL_PROJECTS;
        } else if (user.getRole_id() == Role.EMPLOYEE.getId()) {
            List<ProjectDto> projects = null;
            try {
                projects = projectDAO.findAllByEmployeeID(user.getId());
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute(AttributeName.PROJECTS, projects);
            request.setAttribute(AttributeName.CURRENT_DATE,currentDate);
            return JSPPagePath.EMPLOYEE_PROJECTS;
        } else if(user.getRole_id() == Role.CUSTOMER.getId()) {
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
            request.setAttribute(AttributeName.CURRENT_DATE,currentDate);
            return JSPPagePath.CUSTOMER_PROJECTS;
        }

        try {
            throw new DAOException("Unknown role");
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
    }
}
