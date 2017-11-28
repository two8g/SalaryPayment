public class DeleteEmployeeTransaction implements Transaction {
    private int employeeId;

    public DeleteEmployeeTransaction(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        EmployeeRepositoryImpl.getInstance().delete(employeeId);
    }
}
