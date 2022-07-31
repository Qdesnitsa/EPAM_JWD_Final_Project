package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.dao.dto.EmployeeDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.TeamPositionLevelDAOImpl;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.dao.repository.TeamPositionLevelDAO;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class ShowCustomersGetCommand extends BaseCommand {
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

        int pageSize = 4;
        String pageNumberString = request.getParameter("page_number");
        int pageNumber = null == pageNumberString ? 1 : Integer.parseInt(pageNumberString);
        //TeamPositionLevelDAO teamPositionLevelDAO = new TeamPositionLevelDAOImpl();
        int countCustomers;
        int offset = pageSize * pageNumber - pageSize;
        UserDAO userDAO = new UserDAOImpl();
        //List<CustomerDto> customers = userDAO.findAllCustomers(pageSize,offset);
        List<CustomerDto> customers = userDAO.findAllCustomers(pageSize,offset);
        countCustomers = userDAO.countAllCustomersForAdmin();
        int pageNumbers = (int)Math.ceil(countCustomers/pageSize+0.5);
        request.setAttribute("page_numbers", pageNumbers);
        request.setAttribute("page_number", pageNumber);
        request.setAttribute("page_size", pageSize);
        request.setAttribute(AttributeName.CUSTOMERS, customers);
        return JSPPagePath.ADMIN_ALL_CUSTOMERS;
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
