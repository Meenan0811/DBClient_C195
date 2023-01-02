package Controller;

import DBAccess.ApptSQL;
import DBAccess.CustomerSQL;
import Model.Appt;
import Model.Customers;
import helper.Scenes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private Appt appt = Appt.class.cast(passAppt);
    private ObservableList<Appt> apptList = ApptSQL.getAppts();
    private ObservableList<Customers> custList = CustomerSQL.getAllCust();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        custNameCombo.setItems(custList);
        custNameCombo.setValue(custList.get(1).getName());

        startDate.setValue(appt.getStart().toLocalDate());
        endDate.setValue(appt.getEnd().toLocalDate());

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
