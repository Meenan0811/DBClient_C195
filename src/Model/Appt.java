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

    public Appt (String title,String description, String location, String type, LocalDateTime start, LocalDateTime end, int custId, int userId, int contactId ) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.custId = custId;
        this.userId = userId;
        this.contactId = contactId;
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
    public int getApptId() { return apptId; }

    public String getTitle() { return title; }

    public String getDescription() { return description;}

    public String getLocation() { return location; }

    public LocalDateTime getStart() { return start; }

    public LocalDateTime getEnd() { return end; }

    public int getCustId() { return custId; }

    public int getUserId() { return userId; }

    public int getContactId() { return contactId; }

}
