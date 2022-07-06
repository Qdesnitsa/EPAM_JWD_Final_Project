package by.sidina.it_team.controller.request_processor;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HomePageByRoleProvider {
    public static String getProjectsPageForUser(User user, HttpServletRequest request) throws DAOException {
        ProjectDAO projectDAO = new ProjectDAOImpl();
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if(user.getRole_id() ==  Role.ADMIN.getId()) {
            List<ProjectDto> projects = projectDAO.findAllForAdmin();
            request.setAttribute(AttributeName.PROJECTS, projects);
            return JSPPagePath.ADMIN_ALL_PROJECTS;
        } else if (user.getRole_id() == Role.EMPLOYEE.getId()) {
            List<ProjectDto> projects = projectDAO.findAllByEmployeeID(user.getId());
            request.setAttribute(AttributeName.PROJECTS, projects);
            return JSPPagePath.EMPLOYEE_PROJECTS;
        } else if(user.getRole_id() == Role.CUSTOMER.getId()) {
            List<ProjectDto> projects = projectDAO.findAllByCustomerID(user.getId());
            request.setAttribute(AttributeName.PROJECTS, projects);
            return JSPPagePath.CUSTOMER_PROJECTS;
        }

        throw new DAOException("Unknown role");
    }
}
