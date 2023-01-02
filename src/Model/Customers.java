package Model;

import DBAccess.FLDivisionSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Customers {
    private int custId;
    private String name;
    private String address;
    private String postal;
    private String phone;
    private LocalDateTime createDate;
    private String createBy;
    private LocalDateTime lastUpdate;
    private String updateBy;
    private int divId;
    private String state;
    private ObservableList<FLDivision> flDivList = FXCollections.observableArrayList();

    public Customers(int custId, String name, String address, String postal, String phone,LocalDateTime createDate, String createBy,LocalDateTime lastUpdate, String updateBy, int divId) {
        this.custId = custId;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phone = phone;
        this.createDate = createDate;
        this.createBy = createBy;
        this.lastUpdate = lastUpdate;
        this.updateBy = updateBy;
        this.divId = divId;
    }

    //Set Methods
    public void setCustId(int custId) { this.custId = custId; }

    public void setName(String name) { this.name = name; }

    public void setAddress(String address) { this.address = address; }

    public void setPostal(String postal) {this.postal = postal; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setCreateDate(LocalDateTime date) { this.createDate = date; }

    public void setCreateBy(String createBy) { this.createBy = createBy; }

    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }

    public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }

    public void setDivId(int divId) { this.divId = divId; }

    //Get Methods
    public int getCustId() { return this.custId; }

    public String getName() { return this.name; }

    public String getAddress() { return this.address; }

    public String getPostal() { return this.postal; }

    public String getPhone() { return this.phone; }

    public LocalDateTime getCreateDate() { return this.createDate; }

    public String getCreateBy() { return this.createBy; }

    public LocalDateTime getLastUpdate() { return this.lastUpdate; }

    public String getUpdateBy() { return this.updateBy; }

    public int getDivId() { return this.divId; }

    public String geState(int divId) {
        int temp;
        flDivList = FLDivisionSQL.getAllFl();
        for (FLDivision f : flDivList) {
            temp = f.getDivId();
            if (divId == temp) {
                this.state = f.getDiv();
            }
        }

        return state;
    }


}
