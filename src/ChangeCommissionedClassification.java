public class ChangeCommissionedClassification extends ChangeClassificationTransaction {
    private double salary;
    private double commissionRate;

    public ChangeCommissionedClassification(int employeeId, double salary, double commissionRate) {
        super(employeeId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new BlweeklySchedule(5);
    }
}
