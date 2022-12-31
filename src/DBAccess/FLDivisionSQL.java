package DBAccess;

import Model.FLDivision;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Contains methods to pass SQL commands to database and retrieve information from first_level_divisions table
 *
 * @author Matthew Meenan
 */
public abstract class FLDivisionSQL {

    /**
     * Passes SQL command to database and retrieves all data from first_level_divisions table
     */
    public static ObservableList<FLDivision> getAllFl() {
        ObservableList<FLDivision> allDivList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int divId = rs.getInt(1);
                String div = rs.getString(2);
                LocalDateTime createDate = rs.getTimestamp(3).toLocalDateTime();
                String createBy = rs.getString(4);
                LocalDateTime updateDate = rs.getTimestamp(5).toLocalDateTime();
                String updateBy = rs.getString(6);
                int countryId = rs.getInt(7);

                FLDivision flDiv = new FLDivision(divId, div,createDate, createBy, updateDate, updateBy, countryId);
                allDivList.add(flDiv);


            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return allDivList;
    }



}
