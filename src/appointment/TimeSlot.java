package appointment;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TimeSlot timeSlot = (TimeSlot) obj;
        return startTime.equals(timeSlot.startTime) && endTime.equals(timeSlot.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
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
