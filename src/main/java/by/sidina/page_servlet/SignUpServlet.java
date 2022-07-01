package by.sidina.page_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpServlet extends BasePageServlet {
    public SignUpServlet() {
        super("/page/sign_up/sign_up.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("name",request.getParameter("name"));
        getServletContext().getRequestDispatcher("/page/home/admin_all_projects.jsp").forward(request, response);
    }
}
