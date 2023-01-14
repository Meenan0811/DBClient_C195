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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustController implements Initializable {
    public TextField custNameText;
    public TextField phoneText;
    public TextField addressText;
    public TextField postalText;
    public ComboBox stateCombo;
    public Button saveButton;
    public Button cancelButton;
    public TableView countryTable;
    public ComboBox countryCombo;
    public TableColumn stateCol;
    ObservableList<Countries> countryList = CountriesSQL.getCountries();
    ObservableList<String> country = FXCollections.observableArrayList();
    ObservableList<FLDivision> divList = FLDivisionSQL.getAllFl();


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

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }

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

    /**
     * Returns Division ID of selected FLDivision object from Table
     * @return
     */
    /*public int getDivId() {
        FLDivision currDiv = FLDivision.class.cast(countryTable.getSelectionModel().getSelectedItem());
        int divId = currDiv.getDivId();

        return divId;
    }*/
}
