package Controller;

import DBAccess.UserSQL;
import Model.Appt;
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

import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains methods to verify username and password are correct before allowing user to enter main screen. Determinesusers locale and language based off of system settings.
 *
 * @author Matt Meenan
 */
public class LoginController implements Initializable {

    @FXML
    private Label locLabel;
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
    public static int currUserId;


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
        passLabel.setText(rb.getString("passLabel"));
        locLabel.setText(rb.getString("localLabel"));
        userText.setPromptText(rb.getString("userText"));
        passWordPassField.setPromptText(rb.getString("passField"));

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
                this.currUserId = u.getUserId();

                Scenes.toMain(actionEvent);
                Appt.immediateAppt();

                try {
                    PrintWriter pw = new PrintWriter(new FileOutputStream(
                            new File("login_activty.txt"),
                            true ));
                    pw.append("Login Attempt: " + LocalDate.now() + "\nBy:" + userName + " Succesfull.\n");
                    pw.close();
                }
                catch(FileNotFoundException e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                    System.out.println("File Not Found");
                }
                break;
            }
        }
        if(!valid) {
            Alerts.alertMessage(1);
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(
                        new File("login_activty.txt"),
                        true ));
                pw.append("Login Attempt: " + LocalDate.now() + " at " + Timestamp.valueOf(LocalDateTime.now()) + "\nBy:" + userName + " Failed.\n");
                pw.close();
        }
            catch(FileNotFoundException e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("File Not Found");
            }
        }

    }


}
