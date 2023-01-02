package Controller;

import DBAccess.ApptSQL;
import DBAccess.CustomerSQL;
import Model.Appt;
import Model.Customers;
import helper.Scenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

/**
 *MainWindow controller, populates Appointemnt and customer windows. Passes selected data from Main Controller to next screen
 *
 * @author Matt Meenan
 */
public class MainWinController implements Initializable {
    @FXML
    private Button apptAddButton;
    @FXML
    private Button apptUpdateButton;
    @FXML
    private Button apptDeleteButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button logoutButton;
    @FXML
    private TableColumn apptIdCol;
    @FXML
    private TableView apptTable;
    @FXML
    private TableColumn titleCol;
    @FXML
    private TableColumn descriptionCol;
    @FXML
    private TableColumn locCol;
    @FXML
    private TableColumn contactCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn startCol;
    @FXML
    private TableColumn endCol;
    @FXML
    private TableColumn apptCustIdCol;
    @FXML
    private TableColumn userIdCol;
    @FXML
    private TableView custTable;
    @FXML
    private TableColumn custIdCol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn phoneCol;
    @FXML
    private TableColumn addressCol;
    @FXML
    private TableColumn divIdCol;
    @FXML
    private TableColumn postalCol;
    @FXML
    private Button custAddButton;
    @FXML
    private Button custUpdateButton;
    @FXML
    private Button custDeleteButton;
    @FXML
    private RadioButton weekRadio;
    @FXML
    private RadioButton monthRadio;
    @FXML
    private RadioButton allRadio;
    private LocalDate currDate = LocalDate.now();
    private ObservableList<Appt> allAppt = FXCollections.observableArrayList();
    private ObservableList<Appt> tempAppt = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allSelected();
        allAppt = ApptSQL.getAppts();


    }

    @FXML
    private void monthSelected() {
        tempAppt.clear();
        Month month = currDate.getMonth();
        Month tempMonth;
        int year = currDate.getYear();
        int tempYear;

        monthRadio.setSelected(true);
        weekRadio.setSelected(false);
        allRadio.setSelected(false);

        for (Appt a : allAppt) {
            tempMonth = a.getStart().getMonth();
            tempYear = a.getStart().getYear();
            if (tempMonth.equals(month) && year == tempYear) {
                tempAppt.add(a);
            }
        }
        apptTable.setItems(tempAppt);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("apptId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<Appt, LocalDateTime>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<Appt, LocalDateTime>("end"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("custId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("userId"));
    }

    /**
     * When week view radio button is selected loops through available appointments and displays all appointments within the next 7 days in appointments table
     *
     */
    @FXML
    private void weekSelected() {
        LocalDate date;
        tempAppt.clear();

        monthRadio.setSelected(false);
        weekRadio.setSelected(true);
        allRadio.setSelected(false);


        for(Appt a : allAppt) {
            date = a.getStart().toLocalDate();

            System.out.println("Date Obtained: " + date + " +7 Days: " + date.plusDays(7));
            if (date.equals(currDate) || date.isAfter(currDate) && date.isBefore(currDate.plusDays(7))) {
                tempAppt.add(a);
                System.out.println("Date Added: " + date);
            }
        }
        apptTable.setItems(tempAppt);
        apptIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("apptId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<Appt, LocalDateTime>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<Appt, LocalDateTime>("end"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("custId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("userId"));
    }

    @FXML
    private void allSelected() {
        monthRadio.setSelected(false);
        weekRadio.setSelected(false);
        allRadio.setSelected(true);

        apptTable.setItems(ApptSQL.getAppts());
        apptIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("apptId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Appt, String>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<Appt, LocalDateTime>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<Appt, LocalDateTime>("end"));
        apptCustIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("custId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<Appt, Integer>("userId"));

        custTable.setItems(CustomerSQL.getAllCust());
        custIdCol.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("custId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
        divIdCol.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("divId"));
        postalCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("postal"));

    }


    /**
     * Calls Add appointment screen
     * @param event
     * @throws IOException
     */
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


