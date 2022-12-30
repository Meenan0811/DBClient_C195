package Main;

import Controller.LoginController;
import DBAccess.ApptSQL;
import DBAccess.ContactsSQL;
import Model.Appt;
import Model.Contacts;
import helper.JDBC;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static javafx.scene.input.KeyCode.T;


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
        ObservableList<Contacts> contactList = FXCollections.observableArrayList();
        contactList = ContactsSQL.allContacts();
        ObservableList<Appt> apptList = FXCollections.observableArrayList();
        apptList = ApptSQL.getAppts();
        /*for (Appt a : apptList) {
            System.out.println("Title: " + a.getTitle() + " | Description: " + a.getDescription() + " | Location: " + a.getLocation() + " | Type: " + a.getType() + "\n" + "Start: " + a.getStart() + "\nEnd: " + a.getEnd() + "\nCreated on: " + a.getCreateBy() + " | Customer ID: " + a.getCustId() + " | User ID; " + a.getUserId() + " | Contact ID: " + a.getContactId() + "\nAppointment ID: " + a.getApptId() );
        }*/
        for (Contacts c : contactList) {
            System.out.println("ID: " + c.getContactId() + " Name: " + c.getName() + " Email: " + c.getEmail());
        }
        LocalDateTime dateStart = LocalDateTime.of(2023, 12, 11, 16, 30);
        LocalDateTime dateEnd = LocalDateTime.of(2023, 12, 14, 12, 00);
        /*int add = ApptSQL.addAppt("new", "new", "new", "new", dateStart, dateEnd, 1, 1, 1 );
        System.out.println(add + " rows added");*/
        /*int edit = ApptSQL.editAppt(6, "update", "worked?", "here", "Worthless", dateStart, dateEnd, "new guy", 2, 2, 2);
        System.out.println(edit + " rows updated");*/
        System.out.println(LoginController.currUser);





        launch(args);
        //int delete = ApptSQL.deleteAppt(5);
        //System.out.println(delete + " rows deleted");

        JDBC.closeConnection(); //Closes connection
    }
}
