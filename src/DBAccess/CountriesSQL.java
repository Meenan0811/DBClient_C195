package DBAccess;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesSQL {

    /**
     * Grabs information from Country table and adds to Countries object and List
     * @return ObservableList<Countries></Countries>
     * @author Matt Meenan
     */
    public static ObservableList<Model.Countries> getCountries() {

        ObservableList<Model.Countries> cList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Model.Countries c = new Model.Countries(countryID, countryName);
                cList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cList;
    }
}
