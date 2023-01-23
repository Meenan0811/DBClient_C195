package Model;

import java.time.LocalDateTime;

/**
 * Contains methods and constructors for FLDivision object
 * @author Matthew Meenan
 */
public class FLDivision {

    private int divId;
    private String div;
    private LocalDateTime createDate;
    private String createBy;
    private LocalDateTime updateDate;
    private String updateBy;
    private int countryId;

    /**
     * FLDivision object constructor
     * @param divId
     * @param div
     * @param createDate
     * @param createBy
     * @param updateDate
     * @param updateBy
     * @param countryId
     */
    public FLDivision(int divId, String div,LocalDateTime createDate, String createBy, LocalDateTime updateDate, String updateBy, int countryId) {
        this.divId = divId;
        this.div = div;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
        this.countryId = countryId;
    }


    /**
     * Get methods for FLDivision object
     */
    public int getDivId() { return this.divId; }

    public String getDiv() { return this.div; }

    public LocalDateTime getCreateDate() {return this.createDate; }

    public String getCreateBy() { return this.createBy; }

    public LocalDateTime getUpdateDate() { return this.updateDate; }

    public String getUpdateBy() { return this.updateBy; }

    public int getCountryId() { return this.countryId; }

    /**
     * Returns Division ID of selected FLDivision object from Table
     */
    public static int getDivId(Object object) {
        FLDivision currDiv = FLDivision.class.cast(object);
        int divId = currDiv.getDivId();

        return divId;
    }



}
