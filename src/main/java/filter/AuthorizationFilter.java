package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String role = (String) ((HttpServletRequest) servletRequest).getSession(false).getAttribute("role");

        if (role != null && role.equals("admin")) {

            filterChain.doFilter(servletRequest,servletResponse);

        } else {

            ((HttpServletResponse) servletResponse).sendRedirect("/index.jsp");
        }

    }
}
