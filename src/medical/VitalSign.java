package medical;

public class VitalSign {
    private String signName;
    private double value;
    private String date;

    public VitalSign(String signName, double value, String date) {
        this.signName = signName;
        this.value = value;
        this.date = date;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vital Sign: " + signName + " - " + value + " on " + date;
    }
}
