package Controller;

import DBAccess.CountriesSQL;
import DBAccess.CustomerSQL;
import DBAccess.FLDivisionSQL;
import Model.Countries;
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

/**
 * Contains code that controls Add customer screen
 * @author Matthew Meenan
 */
public class AddCustController implements Initializable {
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
    private TableView countryTable;
    @FXML
    private ComboBox countryCombo;
    @FXML
    private TableColumn stateCol;
    ObservableList<Countries> countryList = CountriesSQL.getCountries();
    ObservableList<String> country = FXCollections.observableArrayList();
    ObservableList<FLDivision> divList = FLDivisionSQL.getAllFl();


    /**
     * OverRides initialize method of Initializable class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Countries c : countryList) {
            this.country.add(c.getName());
        }
        countryCombo.setItems(country);
        countryCombo.setValue("U.S");
        setDivCol();
        countryCombo.setOnAction(event -> setDivCol());


    }

    /**
     * Alerts user that the information entered will not be saved and returns to main screen
     * @param event
     * @throws IOException
     */
    public void toMain(ActionEvent event) throws IOException {
        Alerts.cancel(event);
    }

    /**
     * Retrieves information and, if all fields are filled out correctly, passes information to CustomerSQL addCust method and adds information to database
     * @param event
     * @throws IOException
     */
    public void saveAddCust(ActionEvent event) throws IOException {
        try {
            String custName = custNameText.getText();
            String phone = phoneText.getText();
            String custAdd = addressText.getText();
            String postal = postalText.getText();
            int divId = FLDivision.getDivId(countryTable.getSelectionModel().getSelectedItem());

            if (custName.isEmpty() || phone.isEmpty() || custAdd.isEmpty() || postal.isEmpty()) {
                Alerts.alertMessage(4);
            } else {
                CustomerSQL.addCust(custName, custAdd, postal, phone, LoginController.currUser, LoginController.currUser, divId);
                Scenes.toMain(event);
            }

        }catch(NumberFormatException e) {
            Alerts.alertMessage(4);
            System.out.println("NumberFormat Exception");
        }
        catch(NullPointerException n) {
            Alerts.alertMessage(7);
            System.out.println("NullPointerException");
        }


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
                    for (FLDivision f : divList) {
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
