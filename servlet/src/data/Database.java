package data;
import java.sql.*;

public class Database {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public java.sql.Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata","root", "foobar1337");

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
        return connection;
    }

    public User getUser(Connection connection, String email){
        User user = new User();
        try {
            statement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            statement.setString(1, email);

            resultSet = statement.executeQuery();

            System.out.println("Userdata:");
            while (resultSet.next()) {
                /*
                String rsEmail = resultSet.getString("email");
                String rsFirstName = resultSet.getString("firstName");
                System.out.println("email = " + rsEmail + " & vorname = " + rsFirstName);
                */


                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setCity(resultSet.getString("city"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setPhone(resultSet.getInt("phone"));
                user.setZipcode(resultSet.getString("zipcode"));


            }
        } catch (Exception e) {
            System.out.println("Error:" + e);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (Exception e) {
                System.out.println("Error:" + e);
            }
        return user;
        }
    }

}
