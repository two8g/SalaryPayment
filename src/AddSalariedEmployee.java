public class AddSalariedEmployee extends AddEmployee {
    private double salary;

    public AddSalariedEmployee(int id, String name, String address, double salary) {
        super(id, name, address);
        this.salary = salary;
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
