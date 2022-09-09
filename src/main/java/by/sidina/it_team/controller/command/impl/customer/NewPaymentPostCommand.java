package by.sidina.it_team.controller.command.impl.customer;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.dto.ProjectDto;
import by.sidina.it_team.dao.impl.PaymentDAOImpl;
import by.sidina.it_team.dao.impl.ProjectDAOImpl;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.service.repository.PaymentService;
import by.sidina.it_team.service.repository.ProjectService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.PaymentServiceImpl;
import by.sidina.it_team.service.impl.ProjectServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class NewPaymentPostCommand implements BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ProjectService projectService = new ProjectServiceImpl(new ProjectDAOImpl());
    private static final PaymentService paymentService = new PaymentServiceImpl(new PaymentDAOImpl());

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        return (user != null && user.getRoleId() == Role.CUSTOMER.getId());
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) {
        LocalDate currentDate = LocalDate.now();
        request.setAttribute(AttributeName.CURRENT_DATE, currentDate);
        User user = (User) request.getSession().getAttribute(AttributeName.USER);
        request.setAttribute(AttributeName.USER_NAME, user.getName());
        request.setAttribute(AttributeName.USER_SURNAME, user.getSurname());
        try {
            List<ProjectDto> projects = projectService.findAllByCustomerID(user.getId());
            if (request.getParameter(ParameterName.PROJECT_ID) == null) {
                request.setAttribute(AttributeName.PROJECTS, projects);
                return JSPPagePath.CUSTOMER_PROJECTS;
            } else {
                int projectId = Integer.parseInt(request.getParameter(ParameterName.PROJECT_ID));
                Optional<ProjectDto> project = projectService.findByID(projectId);
                boolean hasCustomerThisProject = projects
                        .stream()
                        .flatMap(s -> Stream.ofNullable(s))
                        .filter(elem -> elem.getId() == projectId)
                        .findAny()
                        .isPresent();
                if (hasCustomerThisProject) {
                    String amount = request.getParameter(ParameterName.PAYMENT);
                    boolean isAdded = paymentService.addPaymentByProjectAndCustomerID(project.get(), amount, Date.valueOf(currentDate));
                    if (isAdded) {
                        request.setAttribute(AttributeName.MESSAGE_SUCCESS, MSG_SUCCESS);
                    }
                } else {
                    request.setAttribute(AttributeName.MESSAGE_FAIL, MSG_FAIL);
                }
                projects = projectService.findAllByCustomerID(user.getId());
                request.setAttribute(AttributeName.PROJECTS, projects);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            return JSPPagePath.ERROR;
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
