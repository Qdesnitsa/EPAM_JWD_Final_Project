package by.sidina.it_team.controller.request_processor;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.entity.UserStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

public class EditEmployeePostRequest extends BaseProcessor{
    private final String MSG_SUCCESS = "Successfully";
    private final String MSG_FAIL = "Operation failed";
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return user != null && user.getRole_id() == Role.ADMIN.getId();
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) session.getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        if (("" == request.getParameter("employee_id") || null == request.getParameter("employee_id"))
        && request.getParameter("add_employee") == null) {
            return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
        } else {
            if (request.getParameter("show_employee") != null) {
                int employeeId = Integer.parseInt(request.getParameter("employee_id"));
                TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
                Optional<EmployeeDto> employee = teamPositionLevelDAO.findByID(employeeId);
                request.setAttribute(AttributeName.EMPLOYEE, employee.get());
                request.setAttribute(AttributeName.EMPLOYEE, employee.get());
                return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
            } else if (request.getParameter("change_status") != null) {
                int employeeId = Integer.parseInt(request.getParameter("employee_id"));
                TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
                Optional<EmployeeDto> employee = teamPositionLevelDAO.findByID(employeeId);
                request.setAttribute(AttributeName.EMPLOYEE, employee.get());
                int status = Integer.parseInt(request.getParameter("change_employee_status"));
                UserDAO userDAO = new UserDAOImpl();
                boolean isChanged = userDAO.changeStatus(employeeId, status);
                if (isChanged) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            } else if (request.getParameter("change_position") != null) {
                int employeeId = Integer.parseInt(request.getParameter("employee_id"));
                TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
                Optional<EmployeeDto> employee = teamPositionLevelDAO.findByID(employeeId);
                request.setAttribute(AttributeName.EMPLOYEE, employee.get());
                int position = Integer.parseInt(request.getParameter("employee_position"));
                boolean isChanged = teamPositionLevelDAO.changePosition(employeeId, position);
                if (isChanged) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            } else if (request.getParameter("change_level") != null) {
                int employeeId = Integer.parseInt(request.getParameter("employee_id"));
                TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
                Optional<EmployeeDto> employee = teamPositionLevelDAO.findByID(employeeId);
                request.setAttribute(AttributeName.EMPLOYEE, employee.get());
                int position = Integer.parseInt(request.getParameter("employee_level"));
                boolean isChanged = teamPositionLevelDAO.changeLevel(employeeId, position);
                if (isChanged) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            } else if (request.getParameter("add_employee") != null) {
                TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();

                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                int status = Integer.parseInt(request.getParameter("employee_status"));
                int role = Integer.parseInt(request.getParameter("employee_role"));
                int position = Integer.parseInt(request.getParameter("employee_position"));
                int level = Integer.parseInt(request.getParameter("employee_level"));
                User userEmployee = new User(name,surname,role,email,status);
                UserDAOImpl userDaoImpl = new UserDAOImpl();
                Optional<User> existingUser = userDaoImpl.findUserByEmail(userEmployee.getEmail());
                if (existingUser.isPresent()) {
                    request.setAttribute("message", "Email already exists");
                    return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
                }
                boolean isChanged = teamPositionLevelDAO.add(position,level,userEmployee,password);
                if (isChanged) {
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);
                }
            }
        }
        return JSPPagePath.ADMIN_EDIT_EMPLOYEE;
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
