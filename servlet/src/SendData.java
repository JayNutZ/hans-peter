import data.Database;
import data.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "/SendData", urlPatterns = {"/SendData"})
public class SendData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Database database = new Database();
        Connection connection = database.connect();
        database.getUser(connection, email);
        response.sendRedirect("done.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Database database = new Database();
        Connection connection = database.connect();
        database.getUser(connection, email);
        response.sendRedirect("done.jsp");
    }
}
