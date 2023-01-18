package DBAccess;

import Model.Customers;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Year;

/**
 * Contains methods to Pass SQL commands to database and retrieve, update, or add new Customer information to Customers table
 *
 * @author Matthew Meenan
 */
public abstract class CustomerSQL {

    /**
     * Passes SQL command to database to retrieve all data from Customers table and add to Customers class object
     *
     * @return ObservableList<Customers>
     */
    public static ObservableList<Customers> getAllCust() {
        ObservableList<Customers> custList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int custId = rs.getInt(1);
                String custName = rs.getString(2);
                String custAddress = rs.getString(3);
                String postal = rs.getString(4);
                String phone = rs.getString(5);
                LocalDateTime createDate = rs.getTimestamp(6).toLocalDateTime();
                String createdBy = rs.getString(7);
                LocalDateTime updateDate = rs.getTimestamp(8).toLocalDateTime();
                String updateBy = rs.getString(9);
                int divId = rs.getInt(10);

                Customers cust = new Customers(custId, custName, custAddress, postal, phone, createDate, createdBy, updateDate, updateBy, divId);
                custList.add(cust);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return custList;
    }

    /**
     * Passes SQL command to database and adds new Customer to customers table
     *
     * @param custName
     * @param custAddress
     * @param postal
     * @param phone
     * @param createBy
     * @param updateBy
     * @param divId
     * @return integer
     */

    public static int addCust(String custName, String custAddress, String postal, String phone, String createBy, String updateBy, int divId) {

        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, now(), ?, now(), ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, custName);
            ps.setString(2, custAddress);
            ps.setString(3, postal);
            ps.setString(4, phone);
            ps.setString(5, createBy);
            ps.setString(6, updateBy);
            ps.setInt(7, divId);

            return ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     *
     *
     */
    public static int editCust(int custId, String custName, String custAddress, String postal, String phone, String updateBy, int divId) {

        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = now(), Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, custName);
            ps.setString(2, custAddress);
            ps.setString(3, postal);
            ps.setString(4,phone);
            ps.setString(5,updateBy);
            ps.setInt(6,divId);
            ps.setInt(7, custId);

            return ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Passes SQL command to delete row from customers table based on customer_ID
     *
     */

    public static int deleteCust(int custId) {

        try {
            String sql = "DELETE from customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, custId);

            return ps.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int custCreated(int year) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(Customer_ID) from customers where year(Create_Date) = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, year);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

       return count;
    }

}
