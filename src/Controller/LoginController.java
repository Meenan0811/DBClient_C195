package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField passWordPassField;
    @FXML
    private TextField userText;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passLabel;
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
