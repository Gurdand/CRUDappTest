package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);

        String role = (String) session.getAttribute("role");

        if (role != null && role.equals("admin")) {

            filterChain.doFilter(servletRequest,servletResponse);

        } else {

            ((HttpServletResponse) servletResponse).sendRedirect("/index.jsp");
        }

    }
}