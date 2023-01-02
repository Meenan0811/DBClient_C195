package helper;

import javafx.scene.control.Alert;

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
    }
}
