package servlet.admin;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String message = "Ошибка удаления записи!";

        try {
            userService.deleteUserById(Integer.parseInt(req.getParameter("id")));
            message = "Запись удалена!";
            resp.setStatus(200);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(400);
        }

        req.setAttribute("message", message);

        getServletContext().getRequestDispatcher("/admin").forward(req,resp);

    }

}
