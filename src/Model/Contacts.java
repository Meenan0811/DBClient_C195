package Model;

/**
 * Contains methods and constructors to create and edit Contacts object
 * @author Matthew Meenan
 */
public class Contacts {

    private int contactId;
    private String name;
    private String email;

    /**
     * Contacts object constructor
     * @param contactId
     * @param name
     * @param email
     */
    public Contacts(int contactId, String name, String email) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
    }

    /**
     * Setter methods for Contacts object
     * @param contactId
     */
    public void setContactId(int contactId) { this.contactId = contactId; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    /**
     * Get methods for Contacts Object
     * @return
     */
    public int getContactId() { return this.contactId; }

    public String getName() { return this.name; }

    public String getEmail() { return this.email; }
}
