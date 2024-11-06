package medical;

public class LabTest {

    private int testID;
    private String testName;
    private String result;
    private String date;

    // Constructor
    public LabTest(int testID, String testName, String result, String date) {
        this.testID = testID;
        this.testName = testName;
        this.result = result;
        this.date = date;
    }

    // Getters
    public int getTestID() {
        return testID;
    }

    public String getTestName() {
        return testName;
    }

    public String getResult() {
        return result;
    }

    public String getDate() {
        return date;
    }

    // Setters
    public void setTestID(int testID) {
        this.testID = testID;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Test ID: " + testID + ", Test Name: " + testName + ", Result: " + result + ", Date: " + date;
    }
}
