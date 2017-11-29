public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(Paycheck paycheck) {
        paycheck.setDisposition("Hold");
    }
}
