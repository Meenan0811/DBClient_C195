package Model;

public class FLDivision {

    private int divId;
    private String div;
    private int countryId;

    public FLDivision(int divId, String div, int countryId) {
        this.divId = divId;
        this.div = div;
        this.countryId = countryId;
    }

    //Set Methods FIXME: This may not be needed
   /* public void setDivId(int divId) { this.divId = divId; }

    public void setDiv(String div) { this.div = div; }

    public void setCountryId(int countryId) { this.countryId = countryId; } */

    //Get Methods
    public int getDivId() { return this.divId; }

    public String getDiv() { return this.div; }

    public int getCountryId() { return this.countryId; }
}
