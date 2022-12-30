package DBAccess;

import Controller.LoginController;
import Model.Appt;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Contains methods to pass SQL commands to database and retrieve or update data
 * @author Matthew Meenan
 */
public abstract class ApptSQL {

    public static ObservableList<Model.Appt> getAppts(){
        ObservableList<Model.Appt> apptList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int apptId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String descr = rs.getString("Description");
                String loc = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                String createBy = rs.getString("Created_By");
                int custId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                Appt appt = new Appt(apptId, title, descr, loc, type, start, end, createBy, custId, userId, contactId);
                apptList.add(appt);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return apptList;
    }

    public static int addAppt(String title,String description, String location, String type, LocalDateTime start, LocalDateTime end,int custId, int userId, int contactId) {
        try {
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, now(), ?, now(), ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setString(7, LoginController.currUser);
            ps.setString(8, LoginController.currUser);
            ps.setInt(9, custId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);

            return ps.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
    //FIXME: SQL error check syntaxt
    public static int editAppt(int apptId, String title,String description, String location, String type, LocalDateTime start, LocalDateTime end, String createBy, int custId, int userId, int contactId) {

        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Created_By = ?, Last_Update = now(), Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4,type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setString(7, createBy);
            ps.setInt(8, custId);
            ps.setInt(9, userId);
            ps.setInt(10, contactId);
            ps.setInt(11, apptId);

            return ps.executeUpdate();
        }

        catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int deleteAppt(int apptID) {
        try{
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, apptID);

            return ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

}
