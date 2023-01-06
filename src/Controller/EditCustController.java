package Controller;

import Model.Customers;
import helper.Scenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controller.MainWinController.passCust;

public class EditCustController implements Initializable {
    public TextField custNameText;
    public TextField phoneText;
    public TextField addressText;
    public TextField postalText;
    public ComboBox stateCombo;
    public Button saveButton;
    public Button cancelButton;
    public TextField custIdText1;
    public ComboBox countryCombo;
    private Customers cust = Customers.class.cast(passCust);
    private ObservableList<String> names = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // for (Customers c : cust)
        custNameText.setText(cust.getName());
        phoneText.setText(cust.getPhone());
        addressText.setText(cust.getAddress());
        postalText.setText(cust.getPostal());
        custIdText1.setText(Integer.toString(cust.getCustId()));

    }

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }

    public void saveEditCust(ActionEvent event) {
    }


}
