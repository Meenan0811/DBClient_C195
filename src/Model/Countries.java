package Model;

/**
 * Contains methods and constructor for Countries object
 * @author Matthew Meenan
 */
public class Countries {
    private int id;
    private String name;

    /**
     * Countires object constructor
     * @param id
     * @param name
     */
    public Countries (int id, String name) {
        this.id = id;
        this.name = name;
    };

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
