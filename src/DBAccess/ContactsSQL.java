package DBAccess;

import Model.Contacts;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods to pass SQL commands to database and retrieve information from Schema appointments Contacts Table
 *
 * @author Matthew Meenan
 */
public abstract class ContactsSQL {

    /**
     * Method to pass SQL commands to database and retrieve information from Contacts table
     * @author Matthew Meenan
     * @return ObservableList<Contacts>
     */
    public static ObservableList<Contacts> allContacts() {
        ObservableList<Contacts> allContact = FXCollections.observableArrayList();

        try{

            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql); //Obtains database Connection and passes SQL command
            ResultSet rs = ps.executeQuery(); //Executes SQL query

            while(rs.next()) { //Obtains information from Contacts table until row is null
                int contactId = rs.getInt(1);
                String contactName = rs.getString(2);
                String email = rs.getString(3);

                Contacts contact = new Contacts(contactId, contactName, email); //Adds information from Contacts table to Contacts object
                allContact.add(contact); //Adds Contacts object to Observable list
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return allContact;
    }

}
