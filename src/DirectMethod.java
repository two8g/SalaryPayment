public class DirectMethod extends PaymentMethod {
    String accountNum;

    public DirectMethod(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountNum() {
        return accountNum;
    }
}
