package Controller;

import DBAccess.CustomerSQL;
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

public class AddApptController implements Initializable {
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
    public Button cancelButton;
    public Button saveButton;
    private final ObservableList<Integer> hours = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23);
    private final ObservableList<Integer> minutes = FXCollections.observableArrayList(00, 15, 30 ,45);
    private final ObservableList<Customers> custList = CustomerSQL.getAllCust();
    private ObservableList<String> names = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Customers c : custList) {
            String name = c.getName();
            names.add(name);
        }
        custNameCombo.setItems(names);

    }

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }


    public void saveAppt(ActionEvent event) {
    }


}
