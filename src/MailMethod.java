public class MailMethod extends PaymentMethod {
    String mailAddress;

    public MailMethod(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }
}
