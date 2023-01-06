package Controller;

import DBAccess.ApptSQL;
import DBAccess.CustomerSQL;
import Model.Appt;
import Model.Customers;
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
    private final Appt appt = Appt.class.cast(passAppt); //FIXME: CAn I do this without casting??
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
        System.out.println(custName);

        //FIXME: Add SetValue based on Customer ID passed
        custNameCombo.setItems(custList);
        custNameCombo.getSelectionModel().select(custName);

        startDate.setValue(appt.getStart().toLocalDate());
        startHrCombo.setItems(hours);
        startMinCombo.setItems(minutes);
        endDate.setValue(appt.getEnd().toLocalDate());
        endHrCombo.setItems(hours);
        endMinCombo.setItems(minutes);
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
    public void saveEditAppt(ActionEvent event) {
    }


}
