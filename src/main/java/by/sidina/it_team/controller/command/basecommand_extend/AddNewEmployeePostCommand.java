package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

public class AddNewEmployeePostCommand extends BaseCommand {
    private final String MSG_SUCCESS = "Successfully";
    private final String MSG_FAIL = "Operation failed";

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return user != null && user.getRole_id() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int status = Integer.parseInt(request.getParameter("employee_status"));
        int role = Integer.parseInt(request.getParameter("employee_role"));
        int position = Integer.parseInt(request.getParameter("employee_position"));
        int level = Integer.parseInt(request.getParameter("employee_level"));
        User userEmployee = new User(name, surname, role, email, status);
        UserDAOImpl userDaoImpl = new UserDAOImpl();
        Optional<User> existingUser = userDaoImpl.findUserByEmail(userEmployee.getEmail());
        if (existingUser.isPresent()) {
            request.setAttribute("message", "Email already exists");
            return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
        }
        boolean isChanged = teamPositionLevelDAO.add(position, level, userEmployee, password);
        if (isChanged) {
            request.setAttribute("message", MSG_SUCCESS);
        } else {
            request.setAttribute("message", MSG_FAIL);
        }
        return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
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
