public class SalariedClassification extends PaymentClassification {
    private double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public double calculatePay(Paycheck paycheck) {
        return salary;
    }

    @Override
    public double calculateDeductions(Paycheck paycheck) {
        return 0;
    }
}
