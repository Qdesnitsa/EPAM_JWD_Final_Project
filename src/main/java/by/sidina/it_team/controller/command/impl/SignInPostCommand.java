package by.sidina.it_team.controller.command.impl;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.entity.UserStatus;
import by.sidina.it_team.service.repository.UserService;
import by.sidina.it_team.service.exception.ServiceException;
import by.sidina.it_team.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class SignInPostCommand implements BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl(new UserDAOImpl());

    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute(AttributeName.USER) != null) {
            return false;
        }
        return true;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String email = request.getParameter(ParameterName.EMAIL);
        String password = request.getParameter(ParameterName.PASSWORD);
        try {
            Optional<User> user = userService.findUserByEmailAndPassword(email, password);
            if (user.isPresent() && user.get().getStatusId() != UserStatus.BLOCKED.getUserStatusID()) {
                session.setAttribute(AttributeName.USER, user.get());
                return JSPPagePath.INDEX;
            } else {
                request.setAttribute(AttributeName.MESSAGE_INVALID_INPUT, MSG_INVALID_EMAIL_PASSWORD);
                return JSPPagePath.SIGN_IN;
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            return JSPPagePath.SIGN_IN;
        }
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return JSPPagePath.ERROR;
    }
}
