package Controller;

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
    }

    //FIXME Determine user Locale and display language
}
