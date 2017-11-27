public abstract class AddEmployee implements EmployeeTransaction {
    int id;
    String name;
    String address;

    public AddEmployee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    abstract PaymentClassification getClassification();

    abstract PaymentSchedule getSchedule();

    abstract PaymentMethod getMethod();
}
