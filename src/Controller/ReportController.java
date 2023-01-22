package Controller;

import DBAccess.ApptSQL;
import DBAccess.ContactsSQL;
import DBAccess.CustomerSQL;
import Model.Appt;
import Model.Contacts;
import Model.Customers;
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
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Class to control the Reports window
 * @author Matthew Meenan
 */
public class ReportController implements Initializable {
    @FXML
    private Label custLabel;
    @FXML
    private ComboBox custCombo;
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
    private ComboBox contNameCombo;
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
    private final ObservableList<Customers> custList = CustomerSQL.getAllCust();
    private ObservableList<String> createBy = FXCollections.observableArrayList();
    private ObservableList<String> apptType = FXCollections.observableArrayList();
    private ObservableList<String> contactName = FXCollections.observableArrayList();
    private ObservableList<Month> months = FXCollections.observableArrayList();



    /**
     * OverRides Initialize class initialize method and sets combo boxes and Tree Table
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setContactApptTable(apptList);
        setComboBox();
        totalApptLabel.setText("");
        custLabel.setText("");

        //Lambda to set Action event for totalApptButton
        totalApptButton.setOnAction(e -> {
            if (typeCombo.getSelectionModel().getSelectedItem() != null && monthCombo.getSelectionModel().getSelectedItem() != null) {
                int totalAppt = ApptSQL.totalApptTM(typeCombo.getValue().toString(), Month.class.cast(monthCombo.getSelectionModel().getSelectedItem()));
                if (totalAppt == 0) {
                    totalApptLabel.setText("No matching appointments found");
                } else if (totalAppt == 1) {
                    totalApptLabel.setText(totalAppt + " Matching appointment found");
                } else {
                    totalApptLabel.setText(totalAppt + " Matching appointments found");
                }
            }
        });

        //Lambda to set change Appointment table based on Contact chosen
        contNameCombo.setOnAction(e -> {
            ObservableList<Appt> aList = FXCollections.observableArrayList();
            int contId = -1;
            String cont = contNameCombo.getValue().toString();
            for (Contacts c : contList) {
                if (c.getName().equals(cont)) {
                    contId = c.getContactId();

                }
            }
            for (Appt a : apptList) {
                if (a.getCustId() == contId) {
                    aList.add(a);
                }

            }
            setContactApptTable(aList);
        });

        //Tallys total Appointments created by selected user and displays the information in a label
        custCombo.setOnAction(e -> {

            if (custCombo.getSelectionModel().getSelectedItem() != null) {
                int apptCreated = 0;
                String apptCreatedBy = custCombo.getValue().toString();
                for (Appt a : apptList) {
                    if (a.getCreateBy().equals(apptCreatedBy)) {
                        apptCreated +=1;
                    }

                }
                if (apptCreated == 0) {
                    custLabel.setText("No appointments created by this User");
                }
                else if (apptCreated == 1) {
                    custLabel.setText("1 appointment created by this user");
                }
                else {
                    custLabel.setText((apptCreated) + " appointments created by this user");
                }
        }});
    }

    /**
     * Sets the Appointment table with all Appointments
     */
    public void setContactApptTable(ObservableList<Appt> appt) {
        contactApptTable.setItems(appt);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("apptId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("type"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("description"));
        startCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("FormatStart"));
        endCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("FormatEnd"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("custId"));
    }

    /**
     * Adds all appropriate information to combo boxes
     */
    public void setComboBox() {
        //Lambda to add all Month values to an months List using Stream and forEach method
        Arrays.stream(Month.values()).forEach(m -> months.add(Month.valueOf(m.name())));

        monthCombo.setItems(months);
        monthCombo.setValue(months.get(0));

        apptType = ApptSQL.apptType();
        typeCombo.setItems(apptType);
        typeCombo.setValue(apptType.get(0));

        String contName;
        for (Contacts c : contList) {
            contName = c.getName();
            contactName.add(contName);
        }
        contNameCombo.setItems(contactName);


        createBy = ApptSQL.apptCreatBy();
        custCombo.setItems(createBy);

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
