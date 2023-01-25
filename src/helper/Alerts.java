package helper;

import DBAccess.ApptSQL;
import DBAccess.CustomerSQL;
import Model.Appt;
import Model.Customers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Alert cast, contains all alert messages used throughout the program
 * @author Matthew Meenan
 */
public abstract class Alerts {

    /**
     * Contains code create and supply appropriate Alert message
     * @param code
     */
    public static void alertMessage(int code) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(code == 1) {
            ResourceBundle rb = ResourceBundle.getBundle("language");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(rb.getString("loginAlert"));
            alert.setHeaderText(rb.getString("loginHeader"));
            alert.showAndWait();
        }
        if(code == 2) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Please select a Appointment to Edit");
            alert.setHeaderText("Warning");
            alert.showAndWait();
        }
        if(code == 3) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Please select a Customer to Edit");
            alert.setHeaderText("Warning");
            alert.showAndWait();
        }
        if(code == 4) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Please ensure all fields are filled out with Appropriate values");
            alert.setHeaderText("Missing or Incorrect Fields");
            alert.showAndWait();
        }
        if(code==5) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Outside of business hours");
            alert.setContentText("Please ensure that the start and end times are within business hours (8am - 10pm EST)");
            alert.showAndWait();
        }
        if(code==6) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Check Start and End times");
            alert.setContentText("Start time must be before End Time and cannot be before today's date and time");
            alert.showAndWait();
        }
        if(code==7) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Missing or Incorrect Fields");
            alert.setContentText("Please choose a Country and appropriate State/Parish/Region");
            alert.showAndWait();
        }
        if(code==8) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("please choose a year");
            alert.setContentText("Please choose a year");
            alert.showAndWait();
        }
        if(code==9) {
            alert.setAlertType((Alert.AlertType.WARNING));
            alert.setHeaderText("Date and Time Overlap");
            alert.setContentText("Selected Date and Time overlap with an existing appointment for the selected Customer");
            alert.showAndWait();
        }

    }

    /**
     * Provides alert informing user the information they have entered will not be saved and returns to main screen
     * @throws IOException
     */
    public static void cancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType((Alert.AlertType.WARNING));
        alert.setHeaderText("Information will not be saved");
        alert.setContentText("Do you want to return to the main screen without saving you information?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get() == ButtonType.OK){
            Scenes.toMain(event);
        }
    }

    /**
     * Provides alert information informing the user that they are about to delete an existing appointment and provides the Appointment ID and type
     * If user accepts calls ApptSQL class deleteAppt method and passes selected Appointment
     * If the user has not selected an appointment alerts the user to select an appointment
     * @param appt
     */
    public static void deleteAlert(Appt appt) {
        try {
            if (appt != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Delete Appointment");
                alert.setContentText("Are you sure you want to delete appointment ID: " + appt.getApptId() + " , type: " + appt.getType() +"? This cannot be undone");
                Optional<ButtonType> confirm = alert.showAndWait();
                if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                    ApptSQL.deleteAppt(appt.getApptId());
                }
                else { throw new NullPointerException(); }
            }
        }
        catch (NullPointerException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Please select a Appointment to delete");
            error.setHeaderText("Delete Appointment");
            error.showAndWait();
        }
    }

    /**
     * Provides alert information informing the user that they are about to delete an existing customer and provides the Customer name.
     * Informs user that all associated appointments with the user ID will also be deleted
     * If user accepts calls ApptSQL class deleteAppt method and passes all appointments with the customer ID as well as CustomerSQL class deleteCust and pass the selcted customer ID
     * If the user has not selected customer alerts the user to select an customer
     * @param cust
     */
    public static void deleteCust(Customers cust) {
        int custId = cust.getCustId();
        ObservableList<Appt> apptList = ApptSQL.getAppts();

        try {
            if (cust != null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Delete Customer");
                alert.setContentText("Are you sure you want to delete customer " + cust.getName() + "? This will delete all appointments assigned to this customer and cannot be undone.");
                Optional<ButtonType> confirm = alert.showAndWait();
                if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                    for (Appt a : apptList) {
                        if (a.getCustId() == custId) {
                            ApptSQL.deleteAppt(a.getApptId());
                        }
                    }
                    CustomerSQL.deleteCust(custId);
                }
            }
            else { throw new NullPointerException(); }
        }catch (NullPointerException n) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Please select a Customer to delete");
            error.setHeaderText("Delete Customer");
            error.showAndWait();
        }
    }

    /**
     * Alerts user if they is a appointment scheduled within the next 15 minutes when called
     * @param apptId
     */
    public static void upcomingAppt(int apptId) {
        ObservableList<Appt> apptList = ApptSQL.getAppts();
        String apptStart = "No Time Found";
        if (apptId > -1) {
            for (Appt a : apptList) {
                if (a.getApptId() == apptId) {
                    apptStart = a.getFormatStart();
                }
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Upcoming Appointment");
            alert.setContentText("Appointment ID: " + apptId + " scheduled for " + apptStart +  " begins soon");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("No Upcoming appointments");
            alert.setContentText("No appointments within the next 15 minutes");
            alert.showAndWait();
        }
    }
}
