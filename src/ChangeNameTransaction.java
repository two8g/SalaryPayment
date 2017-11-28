public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private String name;

    public ChangeNameTransaction(int employeeId, String name) {
        super(employeeId);
        this.name = name;
    }

    @Override
    void change(Employee employee) {
        employee.setName(name);
    }
}
