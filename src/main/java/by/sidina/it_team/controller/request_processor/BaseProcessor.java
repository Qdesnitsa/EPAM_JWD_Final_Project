package by.sidina.it_team.controller.request_processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseProcessor {
    public abstract boolean canBeExpectedResponseReturned(HttpServletRequest request, HttpServletResponse response);
    public abstract String getExpectedJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception;
    public abstract String getAlternativeJspPage(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (canBeExpectedResponseReturned(request, response)) {
            String jspPage = getExpectedJspPage(request, response);
            if (jspPage != null) {
                try {
                    request.getRequestDispatcher(jspPage).forward(request, response);
                } catch (Exception e) {
                    /**
                     logger.log(e);
                     return "error.jsp";
                     */
                    throw new RuntimeException(e);
                }
            }
        } else {
            try {
                request.getRequestDispatcher(getAlternativeJspPage(request, response)).forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
