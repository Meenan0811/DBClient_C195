package Controller;

import DBAccess.ApptSQL;
import DBAccess.ContactsSQL;
import Model.Appt;
import Model.Contacts;
import helper.Scenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

/**
 * Class to control the Reports window
 * @author Matthew Meenan
 */
public class ReportController implements Initializable {
    @FXML
    private Button totalApptButton;
    @FXML
    private Label totalApptLabel;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox typeCombo;
    @FXML
    private ComboBox monthCombo;
    @FXML
    private ComboBox custNameCombo;
    @FXML
    private TableView contactApptTable;
    @FXML
    private TableColumn apptIdCol;
    @FXML
    private TableColumn titleCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn descriptionCol;
    @FXML
    private TableColumn startCol;
    @FXML
    private TableColumn endCol;
    @FXML
    private TableColumn custIdCol;

    private final ObservableList<Appt> apptList = ApptSQL.getAppts();
    private final ObservableList<Contacts> contList = ContactsSQL.allContacts();
    private ObservableList<String> apptMonth = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "October", "November", "December");
    private ObservableList<String> apptType = FXCollections.observableArrayList();
    private ObservableList<String> contactName = FXCollections.observableArrayList();


    /**
     * OverRides Initialize class initialize method and sets combo boxes and Tree Table
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setContactApptTable();
        setComboBox();

    }

    /**
     * Sets the Appointment table with all Appointments
     */
    public void setContactApptTable() {
        contactApptTable.setItems(apptList);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("apptId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("type"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("description"));
        startCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("FormatStart"));
        endCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("FormatEnd"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("custId"));
    }

    public void setComboBox() {
     monthCombo.setItems(apptMonth);
     monthCombo.setValue("January");

     String appTypes;
     for (Appt a : apptList) {
         appTypes = a.getType();
         apptType.add(appTypes);
     }

     typeCombo.setItems(apptType);
    }

    public int totalAppt() {

        return -1;
    }

    /**
     * Returns to Main Screen
     * @param event
     * @throws IOException
     */
    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }


}
