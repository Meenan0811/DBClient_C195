package Controller;

import DBAccess.CountriesSQL;
import DBAccess.CustomerSQL;
import DBAccess.FLDivisionSQL;
import Model.Countries;
import Model.Customers;
import Model.FLDivision;
import helper.Alerts;
import helper.Scenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controller.MainWinController.passCust;

/**
 * Contains code to control Edit user window
 * @author Matthew Meenan
 */
public class EditCustController implements Initializable {
    @FXML
    private TextField custNameText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField postalText;
    @FXML
    private ComboBox stateCombo;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField custIdText1;
    @FXML
    private ComboBox countryCombo;
    @FXML
    private TableView countryTable;
    @FXML
    private TableColumn stateCol;

    private final Customers cust = Customers.class.cast(passCust);
    private final ObservableList<String> country = FXCollections.observableArrayList();
    private final ObservableList<Countries> countryList = CountriesSQL.getCountries();
    private final ObservableList<FLDivision> flDiv = FLDivisionSQL.getAllFl();


    /**
     * PreSets all fields with information from customer Object selected in Main Screen
     * Calls getPassCountry method to set Customers current Country based on their division ID
     * calls setDivCol method to load Divisions column
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Countries c : countryList) {
            this.country.add(c.getName());
        }
        countryCombo.setItems(country);
        countryCombo.setValue(getPassCustCountry());
        setDivCol();
        countryCombo.setOnAction(event -> setDivCol());

        custNameText.setText(cust.getName());
        phoneText.setText(cust.getPhone());
        addressText.setText(cust.getAddress());
        postalText.setText(cust.getPostal());
        custIdText1.setText(Integer.toString(cust.getCustId()));

    }

    /**
     * Alerts user that infromation will not be saved and returns to main window
     * @throws IOException
     */
    public void toMain(ActionEvent event) throws IOException {
        Alerts.cancel(event);
    }

    /**
     * Retrieves information from fields and, if all fields are completed, passes information to CustomerSQL class to update information on database
     * @throws IOException
     */
    public void saveEditCust(ActionEvent event) throws IOException {
        try {
            int custId = Integer.parseInt(custIdText1.getText());
            String custName = custNameText.getText();
            String phone = phoneText.getText();
            String address = addressText.getText();
            String postal = postalText.getText();
            int divId = FLDivision.getDivId(countryTable.getSelectionModel().getSelectedItem());

            if (custName.isEmpty() || phone.isEmpty() || address.isEmpty() || postal.isEmpty()) {
                Alerts.alertMessage(4);
            }
            else {
                CustomerSQL.editCust(custId, custName, address, postal, phone, LoginController.currUser, divId);
                Scenes.toMain(event);
            }


        }catch (NumberFormatException n) {
            Alerts.alertMessage(4);
        }catch (NullPointerException e) {
            Alerts.alertMessage(7);
        }
    }

    /**
     * Retrieves Current country passed from Customer Object by comparing Div Id and Country ID
     * @return String
     */
    public String getPassCustCountry() {
        int divId = cust.getDivId();
        String custCountry = "Not Set";
        for (FLDivision f : flDiv) {
            if (f.getDivId() == divId) {
                int cId = f.getCountryId();
                for (Countries c : countryList) {
                    if (cId == c.getId()) {
                        custCountry = c.getName();
                    }
                }
            }
        }
        return custCountry;
    }

    /**
     * Changes Table to display appropriate state based on selected Country within ComboBox
     */
    public void setDivCol() {
        String temp;
        ObservableList<FLDivision> tempDivList = FXCollections.observableArrayList();
        int countryId = 0;

        if (!countryCombo.getValue().equals(null)) {
            temp = countryCombo.getValue().toString();
            for (Countries c : countryList) {
                if (c.getName().equals(temp)) {
                    countryId = c.getId();
                }
                for (FLDivision f : flDiv) {
                    if (f.getCountryId() == countryId) {
                        tempDivList.add(f);
                    }
                }
            }

            //Lamda's that compare the value of String temp to available countries and sets Column header appropriately
            countryList.forEach(c -> { if (temp.equals("U.S")) stateCol.setText("States");});
            countryList.forEach(c -> { if (temp.equals("UK")) stateCol.setText("Regions");});
            countryList.forEach(c -> { if (temp.equals("Canada")) stateCol.setText("Provinces");});

            countryTable.setItems(tempDivList);
            stateCol.setCellValueFactory(new PropertyValueFactory<>("div"));
            System.out.print(temp + "\n");
        }
    }


}
