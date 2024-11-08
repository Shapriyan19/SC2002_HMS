package user;

import java.time.LocalDateTime;

//Tracks activities such as user logins, logouts, password changes, and access to patient records.
public class AccessLog {

    // Instance variables
    private String logID;               // Unique identifier for each log entry
    private String userID;              // ID of the user performing the action
    private String action;              // Description of the action (e.g., "LOGIN", "VIEW_PATIENT_RECORD")
    private LocalDateTime timestamp;    // Timestamp of when the action occurred
    private String details;             // Additional details about the action (e.g., "Viewed record of Patient ID 12345")

    // Constructor
    public AccessLog(String logID, String userID, String action, LocalDateTime timestamp, String details) {
        this.logID = logID;
        this.userID = userID;
        this.action = action;
        this.timestamp = timestamp;
        this.details = details;
    }

    // Getters and Setters
    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // Method to display log entry details
    public void displayLogDetails() {
        System.out.println("Log ID: " + logID);
        System.out.println("User ID: " + userID);
        System.out.println("Action: " + action);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Details: " + details);
    }
}
