package by.sidina.it_team.controller.request_processor;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.ParameterName;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class NewProjectGetRequest extends BaseProcessor {
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        int projectId = Integer.parseInt(request.getParameter(ParameterName.PROJECT_ID));
        boolean isNewProjectFormRequested = projectId == 0;
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return (user != null && user.getRole_id() == Role.CUSTOMER.getId())
                || !isNewProjectFormRequested;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int projectId = Integer.parseInt(request.getParameter(ParameterName.PROJECT_ID));
        if (projectId == 0) {
            return JSPPagePath.NEW_PROJECT;
        } else {
            ProjectDAO projectDAO = new ProjectDAOImpl();
            Optional<ProjectDto> project = projectDAO.findByID(projectId);
            request.setAttribute(AttributeName.PROJECTS,project);
            return JSPPagePath.CUSTOMER_PROJECTS;
        }
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
