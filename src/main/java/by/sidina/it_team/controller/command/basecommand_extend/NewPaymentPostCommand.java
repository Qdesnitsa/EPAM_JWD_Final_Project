package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.PaymentDAOImpl;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.dao.repository.PaymentDAO;
import by.sidina.it_team.dao.repository.ProjectDAO;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class NewPaymentPostCommand extends BaseCommand {
    private final String MSG_SUCCESS = "Successfully";
    private final String MSG_FAIL = "Failed";
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return (user != null && user.getRole_id() == Role.CUSTOMER.getId());
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User)request.getSession().getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        ProjectDAO projectDAO = null;
        if (request.getParameter(ParameterName.PROJECT_ID) == null) {
            projectDAO = new ProjectDAOImpl();
            List<ProjectDto> projects = projectDAO.findAllByCustomerID(user.getId());
            request.setAttribute(AttributeName.PROJECTS, projects);
            return JSPPagePath.CUSTOMER_PROJECTS;
        } else {
            int projectId = Integer.parseInt(request.getParameter(ParameterName.PROJECT_ID));
            projectDAO = new ProjectDAOImpl();
            Optional<ProjectDto> project = projectDAO.findByID(projectId);
            List<ProjectDto> projects = projectDAO.findAllByCustomerID(user.getId());
            boolean hasCustomerThisProject = projects
                    .stream()
                    .flatMap(s -> Stream.ofNullable(s))
                    .filter(elem -> elem.getId() == projectId)
                    .findAny()
                    .isPresent();
            if (hasCustomerThisProject) {
                PaymentDAO payment = new PaymentDAOImpl();
                double amount = request.getParameter("payment").isEmpty()
                        ? 0
                        : Double.parseDouble(request.getParameter("payment"));
                boolean isAdded = payment.addPaymentByProjectAndCustomerID(project.get(), amount, Date.valueOf(currentDate));
                if (isAdded) {
                    request.setAttribute("message_success", MSG_SUCCESS);
                } else {
                    request.setAttribute("message_fail", MSG_FAIL);
                }
            } else {
                request.setAttribute("message_fail", MSG_FAIL);
            }
            projects = projectDAO.findAllByCustomerID(user.getId());
            request.setAttribute(AttributeName.PROJECTS, projects);
        }
        return JSPPagePath.CUSTOMER_PROJECTS;
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
