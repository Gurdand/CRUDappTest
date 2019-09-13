package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (session != null && session.getAttribute("role") != null ||
                path.equals("/") || path.equals("/index.jsp") || path.equals("/login") ) {
            chain.doFilter(request,response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/");
        }

    }
}
