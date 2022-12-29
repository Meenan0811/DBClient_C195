package helper;

import javafx.scene.control.Alert;

public abstract class Alerts {

    public static void loginAlert(int code) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if(code == 1) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect username or Password. Please try again");
            alert.showAndWait();
        }
    }
}
