package appoinment;

public class TimeSlot {
    private String startTime;
    private String endTime;
    private boolean isAvailable;

    public TimeSlot(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = true; // Default to available
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Getters
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "From: " + startTime + " To: " + endTime;
    }
}


//old code
// public class TimeSlot {

//     private String startTime;
//     private String endTime;

//     public TimeSlot(String startTime, String endTime) {
//         this.startTime = startTime;
//         this.endTime = endTime;
//     }

//     public String getStartTime() {
//         return startTime;
//     }

//     public String getEndTime() {
//         return endTime;
//     }

//     @Override
//     public String toString() {
//         return startTime + " - " + endTime;
//     }
// }
