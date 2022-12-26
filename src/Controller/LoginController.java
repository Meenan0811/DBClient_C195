package Controller;

import DBAccess.DBCountries;
import Model.Countries;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField userText;
    @FXML
    private TextField passText;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Label locLabel;
    @FXML
    private Label locTagLabel;
    @FXML
    private Button submitButton;



    public void setSubmitButton(javafx.event.ActionEvent actionEvent) {
        /*ObservableList<Countries> cList = DBCountries.getCountries();
        for (Countries c : cList) {
            System.out.println("Country ID: " + c.getId() + " Name: " + c.getName());
        }*/ //Example pulling SQL info and displaying in list
    }

    //FIXME Determine user Locale and display language
}
