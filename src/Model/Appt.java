package Model;

import DBAccess.ApptSQL;
import helper.Alerts;
import helper.TimeZones;
import javafx.collections.ObservableList;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

    public Appt (int apptId, String title,String description, String location, String type, LocalDateTime start, LocalDateTime end, String createBy, int custId, int userId, int contactId ) {
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
        this.apptId = apptId;
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


    //FIXME: Add code to verify that date and time are withing operating hours on east coast
    /**
     * Verify that date and time passed are within operating hours and appointment starts before appointment end time
     * @param start
     * @param end
     */
    public static boolean verifyDateTime(LocalDateTime start, LocalDateTime end) {
        boolean range;
        LocalDateTime estStartDT = TimeZones.localToEst(start);
        LocalDateTime estEndDT = TimeZones.localToEst(end);
        LocalTime estStart = estStartDT.toLocalTime();
        LocalTime estEnd = estEndDT.toLocalTime();
        System.out.println("Local Time: " + start + "\nLocal end Time: " + end + "\nEST Start: " + estStart + "\nEST End: " + estEnd);
        LocalTime open = LocalTime.of(8, 00);
        LocalTime close = LocalTime.of(22, 00);
        if (estStart.isBefore(open) || estStart.isAfter(close) || estEnd.isBefore(open) || estEnd.isAfter(close)) {
            Alerts.alertMessage(5);
            range = false;
            return range;
        }
        if (start.isAfter(end) || end.isBefore(start)) {
            Alerts.alertMessage(6);
            range = false;
            return range;
        } else {
            range = true;
        }
    return range;


    }

    /**
     * Checks all appointment times, if appointment is witihin 15 minutes of the current time the user will receive an alert with the appointment ID. If no matching appointment is found the user receives an alert
     *
     */
    public static void immediateAppt() {
        LocalDateTime current = LocalDateTime.now();
        ObservableList<Appt> appts = ApptSQL.getAppts();
        int apptId = -1;
        for (Appt a : appts) {
            LocalDateTime startTime = a.getStart();
            if(startTime.isEqual(current) || startTime.isAfter(current) && startTime.isBefore(current.plusMinutes(16))) {
                apptId = a.getApptId();
                Alerts.upcomingAppt(apptId);
            }
        }
        if (apptId == -1) {
            Alerts.upcomingAppt(apptId);
        }
    }

}
