package Model;

import java.time.LocalDateTime;

public class Appt {
    private int apptId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int custId;
    private int userId;
    private int contactId;
    private String createBy;

    public Appt (String title,String description, String location, String type, LocalDateTime start, LocalDateTime end, String createBy, int custId, int userId, int contactId ) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.custId = custId;
        this.userId = userId;
        this.contactId = contactId;
        this.createBy = createBy;
    }

    //Set Methods
    public void setApptId(int apptId) { this.apptId = apptId; }

    public void setTitle(String title) { this.title = title; }

    public void setDescription(String description) { this.description = description; }

    public void setLocation(String location) { this.location = location; }

    public void setType(String type) { this.type = type; }

    public void setStart(LocalDateTime start) { this.start = start; }

    public void setEnd(LocalDateTime end) { this.end = end; }

    public void setCreateBy(String createBy) { this.createBy = createBy; }

    public void setCustId(int custId) { this.custId = custId; }

    public void setUserId(int userId) { this.userId = userId; }

    public void setContactId(int contactId) { this.contactId = contactId; }

    //Get Methods
    public int getApptId() { return this.apptId; }

    public String getTitle() { return this.title; }

    public String getDescription() { return this.description;}

    public String getLocation() { return this.location; }

    public LocalDateTime getStart() { return this.start; }

    public LocalDateTime getEnd() { return this.end; }

    public int getCustId() { return this.custId; }

    public int getUserId() { return this.userId; }

    public int getContactId() { return this.contactId; }

    public String getType() { return this.type; }

    public String getCreateBy() { return this.createBy; }

}
