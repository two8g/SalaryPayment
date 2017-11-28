public class ChangeMailTransaction extends ChangeMethodTransaction {
    private String mail;

    public ChangeMailTransaction(int employeeId, String mail) {
        super(employeeId);
        this.mail = mail;
    }

    @Override
    PaymentMethod getMethod() {
        return new MailMethod(mail);
    }
}
