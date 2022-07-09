package by.sidina.it_team.controller.request_processor;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.ParameterName;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ShowEmployeesGetRequest extends BaseProcessor {
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return session.getAttribute(AttributeName.USER) != null;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());

        TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
        List<EmployeeDto> employees = teamPositionLevelDAO.findAll();
        request.setAttribute(AttributeName.EMPLOYEES, employees);
        return JSPPagePath.ADMIN_ALL_EMPLOYEES;
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return JSPPagePath.SIGN_IN;
    }
}
