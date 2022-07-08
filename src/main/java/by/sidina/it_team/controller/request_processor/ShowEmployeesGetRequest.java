package by.sidina.it_team.controller.request_processor;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

public class ShowEmployeesGetRequest extends BaseProcessor{
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return session.getAttribute(AttributeName.USER) != null;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(AttributeName.USER);
        if ("" == request.getParameter("project_id") || null == request.getParameter("project_id")) {
            return JSPPagePath.ADMIN_EDIT_PROJECT;
        } else {
            int projectId = Integer.parseInt(request.getParameter("project_id"));
            ProjectDAO projectDAO = new ProjectDAOImpl();
            Optional<ProjectDto> project = projectDAO.findByID(projectId);
            request.setAttribute(AttributeName.PROJECT, project);
            return JSPPagePath.ADMIN_EDIT_PROJECT;
        }
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return JSPPagePath.SIGN_IN;
    }
}
