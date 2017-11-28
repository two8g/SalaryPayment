public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private String account;

    public ChangeDirectTransaction(int employeeId, String account) {
        super(employeeId);
        this.account = account;
    }

    @Override
    PaymentMethod getMethod() {
        return new DirectMethod(account);
    }
}
