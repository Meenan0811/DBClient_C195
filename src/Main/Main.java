package Main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


/**
 * Calls JDBC.openConnection method to connects to database, launches login screen, and closes database connection upon program exit
 * @author Matthew Meenan
 * @version 1.0
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/login.fxml")));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 569, 385));
        primaryStage.show();
    }


    public static void main(String[] args) {
        JDBC.openConnection(); //opens connection
        //System.out.println("JavaFX Version: " + System.getProperty("javafx.version"));

        launch(args);

        JDBC.closeConnection(); //Closes connection
    }
}
