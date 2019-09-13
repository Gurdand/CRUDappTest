package servlet.login;

import model.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = userService.getUserByLogin(login);

            if (user != null && user.getPassword().equals(password)) {

                HttpSession session = req.getSession(true);

                session.setAttribute("role", user.getRole());
                resp.setStatus(200);

                if (user.getRole().equals("admin")) {
                    getServletContext().getRequestDispatcher("/admin").forward(req,resp);

                } else {
                    req.setAttribute("user", user.getName());
                    resp.setStatus(200);
                    getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req,resp);
                }

            } else {
                req.setAttribute("message", "Неправильный Login или пароль!");
                getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Ошибка авторизации!");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
            resp.setStatus(400);
        }


    }
}
