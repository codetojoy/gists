
package net.codetojoy.sandbox;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class MyFilter implements Filter {

    protected String getClient(String queryString) {
        String client = "N/A";

        if (queryString != null) {
            System.out.println("TRACER queryString: " + queryString);
            Pattern queryPattern = Pattern.compile(".*&?client=(.*)&?.*");
            Matcher matcher = queryPattern.matcher(queryString);
            if (matcher.matches()) {
                client = matcher.group(1);
            }
        }

        return client;
    }

    @Override
    public void doFilter(ServletRequest req, 
                         ServletResponse res, 
                         FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

        String client = getClient(request.getQueryString()); 

        String prefix = "TRACER MyFilter " + client + " ::";

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(prefix + " name: " + name + " value: " + value);
            }
        }

        boolean loggedIn = (session != null) && (session.getAttribute("user") != null);

        if (loggedIn) {
            System.out.println(prefix + " WITH user");
        } else {
            System.out.println(prefix + " SETTING user");
            session.setAttribute("user", "5150");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) {}
}

