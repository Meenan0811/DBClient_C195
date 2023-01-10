package Controller;

import DBAccess.UserSQL;
import Model.User;
import helper.Alerts;
import helper.Scenes;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Contains methods to verify username and password are correct before allowing user to enter main screen. Determinesusers locale and language based off of system settings.
 *
 * @author Matt Meenan
 */
public class LoginController implements Initializable {

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
    public static String currUser;


    //FIXME: choose either locale or System for language selection, work on resourcebundle and properties file
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zoneId = ZoneId.systemDefault();
        locTagLabel.setText(zoneId.toString());
        Locale locale = Locale.getDefault(); //Creates locale object
        String lang = locale.getDisplayLanguage(); //Gets default language
        String langCode = System.getProperty("user.language"); // Gets language code
        ResourceBundle rb = ResourceBundle.getBundle("language");
        userNameLabel.setText(rb.getString("userLabel"));


    }

    public void setSubmitButton(javafx.event.ActionEvent actionEvent) throws IOException {
        String userName = userText.getText();
        String pass = passWordPassField.getText();
        ObservableList<Model.User> userList = UserSQL.getUsers();
        boolean valid = false;
        this.currUser = userName;


        for(User u: userList) {
            String tempU = u.getUserName();
            String tempP = u.getPassWord();

            if(userName.contains(tempU) && pass.contains(tempP)) {
                valid = true;

                Scenes.toMain(actionEvent);
                break;
            }
        }
        if(!valid) {
            Alerts.alertMessage(1);
        }

    }


}
