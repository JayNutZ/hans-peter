package data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class HandleData {

    public void respond(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        Database database = new Database();
        Connection connection = database.connect();
        User user = new User();
        database.getUser(user, connection, email);

        System.out.print(   "\n"+"\n"+"\n"+ "Userdata:" + "\n" +
                        user.gender + "\n" +
                        user.firstname + "\n" +
                        user.lastname + "\n" +
                        user.email + "\n" +
                        user.address + "\n" +
                        user.zipcode + "\n" +
                        user.city + "\n" +
                        "+" + user.phone.toString() + "\n" +
                        user.activeSince.toString() + "\n" +
                        user.activeLast.toString() + "\n" +
                        user.salesValue.toString() + "\n" +
                        user.salesCount.toString() + "\n" +
                        user.eventsBooked.toString()
        );
    }
}
