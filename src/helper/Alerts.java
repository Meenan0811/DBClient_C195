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

public abstract class Alerts {

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
            alert.setContentText("Start time must be before End Time");
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

    }

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

    public static void upcomingAppt(int apptId) {
        if (apptId > -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Upcoming Appointment");
            alert.setContentText("Appointment ID: " + apptId + " begins soon");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("No Upcoming appointments");
            alert.setContentText("No appointments within the next 15 minutes");
            alert.showAndWait();
        }
    }
}
