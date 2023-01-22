package Controller;

import DBAccess.ApptSQL;
import DBAccess.ContactsSQL;
import DBAccess.CustomerSQL;
import Model.Appt;
import Model.Contacts;
import Model.Customers;
import helper.Alerts;
import helper.Scenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Contains code to control Add appointment screen
 * @author Matthew Meenan
 */
public class AddApptController implements Initializable {
    @FXML
    private ComboBox custNameCombo;
    @FXML
    private ComboBox contactCombo;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox endHrCombo;
    @FXML
    private ComboBox endMinCombo;
    @FXML
    private ComboBox startHrCombo;
    @FXML
    private ComboBox startMinCombo;
    @FXML
    private TextField titleText;
    @FXML
    private TextField descriptionText;
    @FXML
    private TextField locationText;
    @FXML
    private TextField typeText;
    @FXML
    private TextField userIdText;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    private final ObservableList<Integer> hours = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
    private final ObservableList<String> minutes = FXCollections.observableArrayList("00", "15", "30", "45");
    private final ObservableList<Customers> custList = CustomerSQL.getAllCust();
    private final ObservableList<Contacts> contList = ContactsSQL.allContacts();
    private ObservableList<String> names = FXCollections.observableArrayList();
    private ObservableList<String> contact = FXCollections.observableArrayList();

    /**
     * Override Initialize method
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Customers c : custList) {
            String name = c.getName();
            names.add(name);
        }
        for (Contacts c : contList) {
            String name = c.getName();
            contact.add(name);
        }
        custNameCombo.setItems(names);
        custNameCombo.setValue(names.get(0));
        userIdText.setText(Integer.toString(LoginController.currUserId));
        contactCombo.setItems(contact);
        contactCombo.setValue(contact.get(0));
        startHrCombo.setItems(hours);
        startHrCombo.setValue(hours.get(0));
        startMinCombo.setItems(minutes);
        startMinCombo.setValue(minutes.get(0));
        endHrCombo.setItems(hours);
        endHrCombo.setValue(hours.get(0));
        endMinCombo.setItems(minutes);
        endMinCombo.setValue(minutes.get(0));

        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now());
    }


    /**
     * Gathers information from Combo Boxes and Text Fields, calls ApptSQL class method addAppt to add appointment to database. Calls Appt method verifyDateRange to ensure appointment time is within business hours.
     * @param event
     * @throws IOException
     */
    public void saveAppt(ActionEvent event) throws IOException {

        try {
            LocalDate start = startDate.getValue();
            int startHour = Integer.parseInt(startHrCombo.getValue().toString());
            int startMin = Integer.parseInt(startMinCombo.getValue().toString());
            LocalTime startTime = LocalTime.of(startHour, startMin);
            LocalDateTime startDate = LocalDateTime.of(start, startTime);
            LocalDate end = endDate.getValue();
            int endHour = Integer.parseInt(endHrCombo.getValue().toString());
            int endMin = Integer.parseInt(endMinCombo.getValue().toString());
            LocalTime endTime = LocalTime.of(endHour, endMin);
            LocalDateTime endDate = LocalDateTime.of(end, endTime);
            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String type = typeText.getText();

            int contId = -1;
            for (Contacts c : contList) {
                String tempName = c.getName();
                String currName = contactCombo.getValue().toString();
                if (currName.equals(tempName)) {
                    contId = c.getContactId();
                }
            }
            int custId = -1;
            for (Customers c : custList) {
                String tempName = c.getName();
                String currName = custNameCombo.getValue().toString();
                if (currName.equals(tempName)) {
                    custId = c.getCustId();
                }
            }


            if (title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty()) {
                Alerts.alertMessage(4);

            }
            if (!Appt.verifyDateTime(startDate, endDate)) {

            } else if (title.isEmpty() == false && description.isEmpty() == false && location.isEmpty() == false && type.isEmpty() == false && Appt.verifyDateTime(startDate, endDate) == true) {
                ApptSQL.addAppt(title, description, location, type, startDate, endDate, custId, LoginController.currUserId, contId);
                Scenes.toMain(event);
            }
        } catch (NumberFormatException e) {
            Alerts.alertMessage(4);
        }catch (NullPointerException n) {
            Alerts.alertMessage(4);
        }


    }

    /**
     * Returns to Main screen without Saving Appointment and calls Alert class to provide user alert that the entered information will not be saved
     * @param event
     * @throws IOException
     */
    public void toMain(ActionEvent event) throws IOException {
        Alerts.cancel(event);
    }
}
