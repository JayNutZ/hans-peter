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

    public User getUser(User user, Connection connection, String email){
        try {
            statement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            statement.setString(1, email);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {


                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setCity(resultSet.getString("city"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setPhone(resultSet.getLong("phone"));
                user.setZipcode(resultSet.getString("zipcode"));
                user.setGender(resultSet.getString("gender"));
                user.setActiveSince(resultSet.getDate("activeSince"));
                user.setActiveLast(resultSet.getDate("activeLast"));
                user.setSalesCount(resultSet.getInt("salesCount"));
                user.setSalesValue(resultSet.getDouble("salesValue"));
                user.setEventsBooked(resultSet.getInt("eventsBooked"));


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
