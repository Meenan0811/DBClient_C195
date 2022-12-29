package Controller;

import helper.JDBC;
import helper.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Matt Meenan
 */
public class MainWinController {
    public Button apptAddButton;
    public Button apptUpdateButton;
    public Button apptDeleteButton;
    public Button reportButton;
    public Button logoutButton;
    public TableColumn apptIdCol;
    public TableView apptTable;
    public TableColumn titleCol;
    public TableColumn descriptionCol;
    public TableColumn locCol;
    public TableColumn contactCol;
    public TableColumn typeCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn apptCustIdCol;
    public TableColumn userIdCol;
    public TableView custTable;
    public TableColumn custIdCol;
    public TableColumn nameCol;
    public TableColumn phoneCol;
    public TableColumn addressCol;
    public TableColumn stateCol;
    public TableColumn postalCol;
    public Button custAddButton;
    public Button custUpdateButton;
    public Button custDeleteButton;
    public RadioButton weekRadio;
    public RadioButton monthRadio;


    public void toAddAppt(ActionEvent event) throws IOException {
        Scenes.toAddAppt(event);
    }

    public void toEditAppt(ActionEvent event) throws IOException {
        Scenes.toEditAppt(event);
    }

    public void toAddCust(ActionEvent event) throws IOException {
        Scenes.toAddCust(event);
    }

    public void toEditCust(ActionEvent event) throws IOException {
        Scenes.toEditCust(event);
    }

    public void toReport(ActionEvent event) throws IOException {
        Scenes.toReports(event);
    }

    public void logout(ActionEvent event) throws IOException {
        Scenes.logout(event);
    }
}


