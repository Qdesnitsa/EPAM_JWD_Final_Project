package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.CustomerDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.dao.repository.UserDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

public class ChangeCustomerStatusPostCommand extends BaseCommand {
    private final String MSG_SUCCESS = "Successfully";
    private final String MSG_FAIL = "Operation failed";
    private static final String NO_SUCH_CUSTOMER_ID = "Customer with this ID does not exist.";

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
        if (session.getAttribute(ParameterName.CUSTOMER_ID) == null) {
            return JSPPagePath.ADMIN_EDIT_CUSTOMER;
        } else {
            int customerId = Integer.parseInt(String.valueOf(session.getAttribute("customer_id")));
            UserDAO userDAO = new UserDAOImpl();
            Optional<CustomerDto> customer = userDAO.findCustomerByID(customerId);
            if (customer.isPresent()) {
                int status = null == request.getParameter("change_customer_status")
                        ? customer.get().getStatusId()
                        : Integer.parseInt(request.getParameter("change_customer_status"));
                boolean isChanged = userDAO.changeStatus(customerId, status);
                if (isChanged) {
                    customer = userDAO.findCustomerByID(customerId);
                    request.setAttribute(AttributeName.CUSTOMER, customer.get());
                    request.setAttribute("message", MSG_SUCCESS);
                } else {
                    request.setAttribute("message", MSG_FAIL);

                }
            } else {
                request.setAttribute("message", NO_SUCH_CUSTOMER_ID);
            }
            return JSPPagePath.ADMIN_EDIT_CUSTOMER;
        }
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
