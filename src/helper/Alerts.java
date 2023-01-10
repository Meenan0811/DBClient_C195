package helper;

import DBAccess.ApptSQL;
import Model.Appt;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public abstract class Alerts {

    public static void alertMessage(int code) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(code == 1) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect username or Password. Please try again");
            alert.setHeaderText("Login Incomplete");
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
    }

    public static void deleteAlert(Appt appt) {
        try {
            if (appt != null) {
                Alert alert = new Alert((Alert.AlertType.WARNING));
                alert.setHeaderText("Delete Appointment");
                alert.setContentText("Are you sure you want to delete appointment ID:" + appt.getApptId() + "? This cannot be undone");
                Optional<ButtonType> confirm = alert.showAndWait();
                if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                    ApptSQL.deleteAppt(appt.getApptId());
                }
                else { throw new NullPointerException(); }
            }
        }
        catch (NullPointerException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Please select an Appointment to delete");
            error.setHeaderText("Delete Appointment");
            error.showAndWait();
        }
    }
}
