public class AddSalariedEmployee extends AddEmployee {
    private double salary;

    public AddSalariedEmployee(int id, String name, String address, double salary) {
        super(id, name, address);
        this.salary = salary;
    }

    @Override
    public void execute() {
        Employee employee = new Employee(id, name, address);
        employee.setPaymentClassification(getClassification());
        employee.setPaymentSchedule(getSchedule());
        employee.setPaymentMethod(getMethod());
        EmployeeRepositoryImpl.getInstance().addEmployee(employee);
    }

    @Override
    PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
