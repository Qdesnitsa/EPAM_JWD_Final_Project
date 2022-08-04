package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.TeamScheduleDAOImpl;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.dao.repository.TeamScheduleDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.TeamSchedule;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class PostHoursPostCommand extends BaseCommand {
    public static int HOURS_DEFAULT = 0;

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return user != null
                && user.getRole_id() == Role.EMPLOYEE.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        int projectId = Integer.parseInt(String.valueOf(session.getAttribute(AttributeName.PROJECT_ID)));
        ProjectDAO projectDAO = new ProjectDAOImpl();
        List<ProjectDto> employeeProjects = projectDAO.findAllByEmployeeID(user.getId());
        boolean hasEmployeeThisProject = employeeProjects
                .stream()
                .flatMap(s -> Stream.ofNullable(s))
                .filter(elem -> (elem.getId() == projectId))
                .findAny()
                .isPresent();
        if (hasEmployeeThisProject) {
            Date date = null == request.getParameter(ParameterName.DATE)
                    ? Date.valueOf(currentDate)
                    : Date.valueOf(request.getParameter(ParameterName.DATE));
            double hours = null == request.getParameter(ParameterName.HOURS)
                    ? HOURS_DEFAULT
                    : Double.parseDouble(request.getParameter(ParameterName.HOURS));
            TeamScheduleDAO teamScheduleDAO = new TeamScheduleDAOImpl();
            boolean isAdded = teamScheduleDAO.addHoursByEmployeeId(new TeamSchedule(user.getId(), projectId, date, hours));
            if (isAdded) {
                request.setAttribute(AttributeName.MESSAGE_SUCCESS, MSG_SUCCESS);
            } else {
                request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
            }
        } else {
            request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
        }
        return JSPPagePath.EMPLOYEE_EDIT_HOURS;
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
