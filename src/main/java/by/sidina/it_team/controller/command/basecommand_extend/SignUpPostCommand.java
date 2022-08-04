package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.command.dictionary.AttributeName;
import by.sidina.it_team.controller.command.dictionary.JSPPagePath;
import by.sidina.it_team.controller.command.dictionary.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.entity.Role;
import by.sidina.it_team.entity.User;
import by.sidina.it_team.entity.UserStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.sidina.it_team.controller.command.dictionary.MessageContent.*;

public class SignUpPostCommand extends BaseCommand {
    @Override
    public boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute(AttributeName.USER) != null) {
            return false;
        }
        return true;
    }

    @Override
    public String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        String name = request.getParameter(ParameterName.NAME);
        String surname = request.getParameter(ParameterName.SURNAME);
        String email = request.getParameter(ParameterName.EMAIL);
        String password = request.getParameter(ParameterName.PASSWORD);
        User user = new User(name, surname, Role.CUSTOMER.getId(), email, UserStatus.ACTIVE.getUserStatusID());
        UserDAOImpl userDaoImpl = new UserDAOImpl();
        Optional<User> existingUser = userDaoImpl.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            request.setAttribute(AttributeName.MESSAGE_EMAIL_EXISTS, MSG_EMAIL_EXISTS);
            return JSPPagePath.SIGN_UP;
        }
        userDaoImpl = new UserDAOImpl();
        userDaoImpl.add(user, password);
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.USER, user);
        return JSPPagePath.INDEX;
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return JSPPagePath.ERROR;
    }
}
