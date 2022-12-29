package Controller;

import helper.Scenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
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



    public void setSubmitButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent mainWin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/mainwin.fxml")));
        Scene scene = new Scene(mainWin);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Schedule");
        stage.setScene(scene);
        stage.show();


    }

    //FIXME: choose either locale or System for language selection
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zoneId = ZoneId.systemDefault();
        locTagLabel.setText(zoneId.toString());
        Locale locale = Locale.getDefault(); //Creates locale object
        String lang = locale.getDisplayLanguage(); //Gets default language
        String langCode = System.getProperty("user.language"); // Gets language code


    }

}
