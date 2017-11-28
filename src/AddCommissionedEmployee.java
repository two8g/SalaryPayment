public class AddCommissionedEmployee extends AddEmployee {
    private double salary;
    private double commissionedRate;

    public AddCommissionedEmployee(int id, String name, String address, double salary, double commissionedRate) {
        super(id, name, address);
        this.salary = salary;
        this.commissionedRate = commissionedRate;
    }

    @Override
    PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionedRate);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new BlweeklySchedule(5);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
