package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class ShowEmployeesGetCommand extends BaseCommand {
    public static final int PAGE_NUMBER_DEFAULT = 1;
    public static final int PAGE_SIZE_DEFAULT = 5;

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return user != null
                && user.getRole_id() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        int pageSize = PAGE_SIZE_DEFAULT;
        String pageNumberString = request.getParameter(ParameterName.PAGE_NUMBER);
        int pageNumber = null == pageNumberString
                ? PAGE_NUMBER_DEFAULT
                : Integer.parseInt(pageNumberString);
        TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
        int countEmployees;
        int offset = pageSize * pageNumber - pageSize;
        List<EmployeeDto> employees = teamPositionLevelDAO.findAllForAdmin(pageSize, offset);
        countEmployees = teamPositionLevelDAO.countAllEmployeesForAdmin();
        int pageNumbers = (int) Math.ceil(countEmployees / pageSize + 0.5);
        request.setAttribute(AttributeName.PAGE_QUANTITY, pageNumbers);
        request.setAttribute(AttributeName.PAGE_NUMBER, pageNumber);
        request.setAttribute(AttributeName.PAGE_SIZE, pageSize);
        request.setAttribute(AttributeName.EMPLOYEES, employees);
        return JSPPagePath.ADMIN_ALL_EMPLOYEES;
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
