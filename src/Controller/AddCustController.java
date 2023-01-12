package Controller;

import DBAccess.CountriesSQL;
import DBAccess.FLDivisionSQL;
import Model.Countries;
import Model.FLDivision;
import helper.Scenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
            String country = c.getName();
            this.country.add(country);
        }
        countryCombo.setItems(country);
        countryTable.setItems(divList);




    }

    public void toMain(ActionEvent event) throws IOException {
        Scenes.toMain(event);
    }

    public void saveAddCust(ActionEvent event) {
    }

    public void getCountry() {
        stateCol.setCellValueFactory(new PropertyValueFactory<FLDivision, String>("div"));
    }


}
