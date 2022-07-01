package by.sidina.page_servlet;

import javax.servlet.annotation.*;

@WebServlet("/sign-in")
public class SignInServlet extends BasePageServlet {
   public SignInServlet() {
       super("/page/sign_in/sign_in.jsp");
   }
}
