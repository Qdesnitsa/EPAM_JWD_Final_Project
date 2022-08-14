package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectCalculationDAOImpl;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.repository.ProjectCalculationDAO;
import by.sidina.it_team.entity.ProjectStatus;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.ProjectCalculationService;
import by.sidina.it_team.service.repository.ProjectService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.ProjectCalculationServiceImpl;
import by.sidina.it_team.service.impl.ProjectServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class RemoveCalculationProjectPostCommand implements BaseCommand {
    private static final ProjectService projectService = new ProjectServiceImpl(new ProjectDAOImpl());
    private static final ProjectCalculationService projectCalculationService
            = new ProjectCalculationServiceImpl(new ProjectCalculationDAOImpl());

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return user != null && user.getRole_id() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if (session.getAttribute(AttributeName.PROJECT_ID) == null) {
            return JSPPagePath.ADMIN_EDIT_PROJECT;
        } else {
            int projectId = Integer.parseInt(String.valueOf(session.getAttribute(AttributeName.PROJECT_ID)));
            Optional<ProjectDto> project = projectService.findByID(projectId);
            if (project.isPresent()) {
                boolean isAdded = projectCalculationService.remove(projectId);
                boolean isChanged = projectService.changeStatus(projectId, String.valueOf(ProjectStatus.NEW.getProjectStatusID()));
                if (isAdded) {
                    request.setAttribute(AttributeName.MESSAGE_SUCCESS, MSG_SUCCESS);
                    project = projectService.findByID(projectId);
                    request.setAttribute(AttributeName.PROJECT, project.get());
                    return JSPPagePath.ADMIN_EDIT_PROJECT;
                } else {
                    request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
                }
            } else {
                request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
            }
        }
        return JSPPagePath.ADMIN_EDIT_PROJECT;
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
