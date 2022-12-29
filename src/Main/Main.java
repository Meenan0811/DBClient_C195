package Main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;



public class Main extends Application {



    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(""));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/login.fxml")));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 569, 385));
        primaryStage.show();
    }


    public static void main(String[] args) {
        JDBC.openConnection(); //opens connection
        //FIXME: Remove before submission
        //Locale.setDefault(new Locale("fr"));
        launch(args);

        JDBC.closeConnection(); //Closes connection
    }
}
