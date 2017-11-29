public class MailMethod implements PaymentMethod {
    String mailAddress;

    public MailMethod(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    @Override
    public void pay(Paycheck paycheck) {
        paycheck.setDisposition("Mail:" + mailAddress);
    }
}
