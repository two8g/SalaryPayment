public class HourlyClassification extends PaymentClassification {
    private double hourlySalary;

    public HourlyClassification(double hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public double getHourlySalary() {
        return hourlySalary;
    }
}
