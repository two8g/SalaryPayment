public abstract class ChangeClassificationTransaction implements Transaction {
    protected int employeeId;

    public ChangeClassificationTransaction(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        Employee employee = EmployeeRepositoryImpl.getInstance().getEmployee(employeeId);
        employee.setPaymentClassification(getClassification());
        employee.setPaymentSchedule(getSchedule());
    }

    abstract PaymentClassification getClassification();

    abstract PaymentSchedule getSchedule();
}
