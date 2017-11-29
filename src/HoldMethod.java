public class HoldMethod extends PaymentMethod {
    @Override
    public void pay(Paycheck paycheck) {
        paycheck.setDisposition("Hold");
    }
}
