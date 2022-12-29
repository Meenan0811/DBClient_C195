package Controller;

import helper.Scenes;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditApptController {
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

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }

    public void saveEditAppt(ActionEvent event) {
    }
}
