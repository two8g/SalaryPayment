public class AddHourlyEmployee extends AddEmployee {
    private static final int FRIDAY = 5;
    double hourSalary;

    public AddHourlyEmployee(int id, String name, String address, double hourSalary) {
        super(id, name, address);
        this.hourSalary = hourSalary;
    }

    @Override
    PaymentClassification getClassification() {
        return new HourlyClassification(hourSalary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new WeeklySchedule(FRIDAY);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
