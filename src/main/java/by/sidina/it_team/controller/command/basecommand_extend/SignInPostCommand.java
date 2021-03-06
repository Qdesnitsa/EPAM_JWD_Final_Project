package by.sidina.it_team.controller.command.basecommand_extend;

import by.sidina.it_team.controller.AttributeName;
import by.sidina.it_team.controller.JSPPagePath;
import by.sidina.it_team.controller.ParameterName;
import by.sidina.it_team.controller.command.BaseCommand;
import by.sidina.it_team.dao.exception.DAOException;
import by.sidina.it_team.dao.impl.UserDAOImpl;
import by.sidina.it_team.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInPostCommand extends BaseCommand {
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
        HttpSession session = request.getSession();
        String email = request.getParameter(ParameterName.EMAIL);
        String password = request.getParameter(ParameterName.PASSWORD);
        UserDAOImpl userDaoImpl = new UserDAOImpl();
        Optional<User> user = userDaoImpl.findUserByEmailAndPassword(email, password);
        if(user.isPresent() && user.get().getStatus_id() != 2) {
            session.setAttribute(AttributeName.USER, user.get());
            return JSPPagePath.INDEX;
        } else {
            request.setAttribute("error", "Invalid email or password");
            return JSPPagePath.SIGN_IN;
        }
    }

    @Override
    public String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) {
        return "/page/error/error.jsp";
    }
}
