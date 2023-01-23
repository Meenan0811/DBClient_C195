package helper;

import Controller.MainWinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contains methods to call each window
 */
public abstract class Scenes {


    /**
     * Calls the main window
     * @param event
     * @throws IOException
     */
    public static void toMain(ActionEvent event) throws IOException {
        FXMLLoader mainWin = new FXMLLoader(MainWinController.class.getResource("../View/mainWin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(mainWin.load(), 1067, 656);
        stage.setTitle("Appointment Schedule");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calls the add Customer window
     * @param event
     * @throws IOException
     */
    public static void toAddCust(ActionEvent event) throws IOException {
        FXMLLoader addCust = new FXMLLoader(MainWinController.class.getResource("../View/addCust.fxml"));
        Scene scene = new Scene(addCust.load(),734,386);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Calls the edit Customer window
     * @param event
     * @throws IOException
     */
    public static void toEditCust(ActionEvent event) throws IOException {
        FXMLLoader editCust = new FXMLLoader(MainWinController.class.getResource("../View/editCust.fxml"));
        Scene scene = new Scene(editCust.load(),734, 386);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Edit Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calls the add Appointment window
     * @param event
     * @throws IOException
     */
    public static void toAddAppt(ActionEvent event) throws IOException {
        FXMLLoader addAppt = new FXMLLoader(MainWinController.class.getResource("../View/addAppt.fxml"));
        Scene scene = new Scene(addAppt.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add New Appointment");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Calls the edit Appointment window
     * @param event
     * @throws IOException
     */
    public static void toEditAppt(ActionEvent event) throws IOException {
        FXMLLoader editAppt = new FXMLLoader(MainWinController.class.getResource("../View/editAppt.fxml"));
        Scene scene = new Scene(editAppt.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Edit Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calls the Reports window
     * @param event
     * @throws IOException
     */
    public static void toReports(ActionEvent event) throws IOException {
        FXMLLoader report = new FXMLLoader(MainWinController.class.getResource("../View/report.fxml"));
        Scene scene = new Scene(report.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calls the JDBC class closeConnection method and exits the program
     * @param quit
     */
    public static void logout(ActionEvent quit) {
        JDBC.closeConnection();
        System.exit(0);
    }

}
