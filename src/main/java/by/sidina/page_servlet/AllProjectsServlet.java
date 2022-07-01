package by.sidina.page_servlet;

import by.sidina.it_team.entity.Project;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AllProjectsServlet", value = "/admin-home/all-projects")
public class AllProjectsServlet extends BasePageServlet {

    public AllProjectsServlet() {
        super("/page/home/admin_all_projects.jsp");
    }

    @Override
    public void prepareDataForPage(HttpServletRequest request) throws ParseException {
//        List<Project> projects = Arrays.asList(
//                new Project("Project 1", "Project for user from USA"),
//                new Project("Project 2", "Training for employees"),
//                new Project("Project 3", "Testing")
//                new Project("Project 4", "Smth new"),
//                new Project("Project 5", "Optimization")
//        );
//        List <ProjectPeriod> projectPeriod = Arrays.asList(
//                new ProjectPeriod(1,new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-18"),
//                        new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-18"), 1),
//                new ProjectPeriod(2,new SimpleDateFormat("yyyy-MM-dd").parse("2011-02-18"),
//                        new SimpleDateFormat("yyyy-MM-dd").parse("2012-09-18"), 1),
//                new ProjectPeriod(3,new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-18"),
//                        new SimpleDateFormat("yyyy-MM-dd").parse("2013-01-18"), 1)
//        );
//        request.setAttribute("projects", projects);
//        request.setAttribute("projectPeriod", projectPeriod);

        HttpSession session = request.getSession();
        request.setAttribute("email",session.getAttribute("email"));
    }
}
