public class DirectMethod implements PaymentMethod {
    String accountNum;

    public DirectMethod(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountNum() {
        return accountNum;
    }

    @Override
    public void pay(Paycheck paycheck) {
        paycheck.setDisposition("Account:" + accountNum);
    }
}
