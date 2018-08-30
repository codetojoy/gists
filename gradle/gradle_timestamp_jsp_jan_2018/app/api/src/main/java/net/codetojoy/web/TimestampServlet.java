
package net.codetojoy.web;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TimestampServlet extends HttpServlet {
    private final Reader reader = new Reader();

    protected String generateBody() {
        StringBuilder buffer = new StringBuilder();

        String utilsBuildTimestamp = reader.read("autogen_utils_build_timestamp.properties");
        String serviceBuildTimestamp = reader.read("autogen_service_build_timestamp.properties");

        buffer.append(" <hr/><p>utils   : " + utilsBuildTimestamp + "</p>");
        buffer.append(" <hr/><p>service : " + serviceBuildTimestamp + "</p>");

        return buffer.toString();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();        

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>From Servlet</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println( generateBody() ) ;
        writer.println("</body>");
        writer.println("</head>");
        writer.println("</html>");
    }
}
