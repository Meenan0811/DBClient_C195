package Controller;

import DBAccess.ApptSQL;
import DBAccess.CustomerSQL;
import Model.Appt;
import Model.Customers;
import helper.Alerts;
import helper.Scenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;

import static Controller.MainWinController.passAppt;

/**
 * Information is passed from Appointment table and edited by user. Once user saves information the Appointment is updated int the SQL database
 *
 * @author Matthew Meenan
 */
public class EditApptController implements Initializable {
    public ComboBox custNameCombo;
    public DatePicker startDate;
    public DatePicker endDate;
    public ComboBox endHrCombo;
    public ComboBox endMinCombo;
    public ComboBox startHrCombo;
    public ComboBox startMinCombo;
    public TextField titleText;
    public TextField descriptionText;
    public TextField locationText;
    public TextField typeText;
    public TextField userIdText;
    public Button saveButton;
    public Button cancelButton;
    private String custName;
    private final Appt appt = Appt.class.cast(passAppt);
    private final ObservableList<Appt> apptList = ApptSQL.getAppts();
    private final ObservableList<Customers> custList = CustomerSQL.getAllCust();
    private ObservableList<String> names = FXCollections.observableArrayList();
    private final ObservableList<Integer> hours = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);
    private final ObservableList<Integer> minutes = FXCollections.observableArrayList(00, 15, 30 ,45);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Customers c : custList) {
            String name = c.getName();
            names.add(name);
        }
        for (Customers c : custList) {
            int tempId = c.getCustId();
            int tempAId = appt.getCustId();
                if (tempId == tempAId) {
                    custName = c.getName();
                }
            }



        custNameCombo.setItems(names);
        custNameCombo.getSelectionModel().select(custName);
        startDate.setValue(appt.getStart().toLocalDate());
        startHrCombo.setItems(hours);
        startHrCombo.setValue(appt.getStart().toLocalTime().getHour());
        startMinCombo.setItems(minutes);
        startMinCombo.setValue(appt.getStart().toLocalTime().getMinute());
        endDate.setValue(appt.getEnd().toLocalDate());
        endHrCombo.setItems(hours);
        endHrCombo.setValue(appt.getEnd().toLocalTime().getHour());
        endMinCombo.setItems(minutes);
        endMinCombo.setValue(appt.getEnd().toLocalTime().getMinute());
        titleText.setText(appt.getTitle());
        descriptionText.setText(appt.getDescription());
        locationText.setText(appt.getLocation());
        typeText.setText(appt.getType());
        userIdText.setText(Integer.toString(appt.getUserId()));
    }

    /**
     * returns to main screen
     * @param event
     * @throws IOException
     */
    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }

    /**
     * Calls method to pass SQL commands and update database with provided information
     * @param event
     */
    public void saveEditAppt(ActionEvent event) throws IOException {

        try {
            String name = custNameCombo.getValue().toString();
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
            int userId = Integer.parseInt(userIdText.getText());
            int apptId = appt.getApptId();
            int contactId = appt.getContactId();

            String custName = custNameCombo.getValue().toString();
            int custId = -1;
            for (Customers c : custList) {
                if (c.getName().equals(custName)) {
                    custId = c.getCustId();
                }
            }

            if(title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty()) {
                Alerts.alertMessage(4);
            }
            if(!Appt.verifyDateTime(startDate, endDate)) {

            }
            else if (title.isEmpty() == false && description.isEmpty() == false && location.isEmpty() == false && type.isEmpty() == false && Appt.verifyDateTime(startDate, endDate) == true) {
                ApptSQL.editAppt(apptId, title, description, location, type, startDate, endDate, LoginController.currUser, custId, userId, contactId);
                toMain(event);
            }
        } catch (NumberFormatException n) {
            Alerts.alertMessage(4);
        }
    }

}
