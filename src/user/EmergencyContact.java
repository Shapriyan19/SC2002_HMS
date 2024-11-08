package user;

public class EmergencyContact {

    // Instance variables
    private String contactID;       // Unique ID for the emergency contact
    private String name;            // Name of the emergency contact
    private String relationship;    // Relationship to the primary user (e.g., "Parent", "Sibling")
    private String phoneNumber;     // Contact's phone number
    private String email;           // Contact's email address (optional)
    
    // Constructor
    public EmergencyContact(String contactID, String name, String relationship, String phoneNumber, String email) {
        this.contactID = contactID;
        this.name = name;
        this.relationship = relationship;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to display contact details
    public void displayContactDetails() {
        System.out.println("Emergency Contact Details:");
        System.out.println("Name: " + name);
        System.out.println("Relationship: " + relationship);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + (email != null ? email : "N/A"));
    }

    // Method to update contact details
    public void updateContactDetails(String name, String relationship, String phoneNumber, String email) {
        this.name = name;
        this.relationship = relationship;
        this.phoneNumber = phoneNumber;
        this.email = email;
        System.out.println("Emergency contact details updated.");
    }
}
