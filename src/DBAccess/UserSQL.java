package DBAccess;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods to pass SQL commands to database and retrieve information from Users table
 * @author Matthew Meenan
 */
public abstract class UserSQL {

    /**
     * PAsses SQL commands to database to retrieve all User names and Passwords
     * @return ObservableList<user>
     */
    public static ObservableList<Model.User> getUsers() {
        ObservableList<Model.User> userList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userID = rs.getInt("User_ID");
                String username = rs.getString("User_Name");
                String password = rs.getString("Password");
                Model.User user = new Model.User(userID, username, password);
                userList.add(user);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
