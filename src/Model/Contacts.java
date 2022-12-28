package Model;

public class Contacts {

    private int contactId;
    private String name;
    private String email;

    public Contacts(int contactId, String name, String email) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
    }

    //Set Methods
    public void setContactId(int contactId) { this.contactId = contactId; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    //Get Methods
    public int getContactId() { return this.contactId; }

    public String getName() { return this.name; }

    public String getEmail() { return this.email; }
}
