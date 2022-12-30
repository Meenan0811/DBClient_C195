package DBAccess;

import Model.Contacts;
import helper.JDBC;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class ContactsSQL {


    public static ObservableList<Contacts> allContacts() {
        ObservableList<Contacts> allContact = FXCollections.observableArrayList();

        try{

            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int contactId = rs.getInt(1);
                String contactName = rs.getString(2);
                String email = rs.getString(3);

                Contacts contact = new Contacts(contactId, contactName, email);
                allContact.add(contact);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return allContact;
    }

}
