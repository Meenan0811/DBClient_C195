package Controller;

import helper.Scenes;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditCustController {
    public TextField custNameText;
    public TextField phoneText;
    public TextField addressText;
    public TextField postalText;
    public ComboBox stateCombo;
    public Button saveButton;
    public Button cancelButton;
    public TextField custIdText1;
    public ComboBox countryCombo;

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }

    public void saveEditCust(ActionEvent event) {
    }
}
