public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private double hourlySalary;

    public ChangeHourlyTransaction(int employeeId, double hourlySalary) {
        super(employeeId);
        this.hourlySalary = hourlySalary;
    }

    @Override
    PaymentClassification getClassification() {
        return new HourlyClassification(hourlySalary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new WeeklySchedule(HourlyClassification.FRIDAY);
    }
}
