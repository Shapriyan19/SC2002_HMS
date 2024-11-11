package utilities;

public class IDGenerator {
    private static int currentID = 1; // Start from 1, for example

    // Synchronized to ensure thread safety if multiple users are created concurrently
    public static synchronized int generateID() {
        return currentID++;
    }
}
