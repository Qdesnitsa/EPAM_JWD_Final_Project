package by.sidina.it_team.controller.request_processor;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.dao.impl.TeamScheduleDAOImpl;
import by.sidina.it_team.dao.repository.TeamScheduleDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.TeamSchedule;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

public class PostHoursPostRequest extends BaseProcessor {
    private final String MSG_SUCCESS = "Successfully";
    private final String MSG_FAIL = "Operation failed";

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return user != null
                && user.getRole_id() == Role.EMPLOYEE.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if ("" == request.getParameter("date") || "0".equals(request.getParameter("project_id"))
                || "" == request.getParameter("hours")) {
            return JSPPagePath.EMPLOYEE_EDIT_HOURS;
        } else {
            int projectId = Integer.parseInt(request.getParameter("project_id"));
            Date date = Date.valueOf(request.getParameter("date"));
            double hours = Double.parseDouble(request.getParameter("hours"));
            TeamScheduleDAO teamScheduleDAO = new TeamScheduleDAOImpl();
            boolean isAdded = teamScheduleDAO.addHoursByEmployeeId(new TeamSchedule(user.getId(), projectId, date, hours));
            if (isAdded) {
                request.setAttribute("message", MSG_SUCCESS);
            } else {
                request.setAttribute("message", MSG_FAIL);
            }
        }
        return JSPPagePath.EMPLOYEE_EDIT_HOURS;
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
