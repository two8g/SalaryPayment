public class BlweeklySchedule implements PaymentSchedule {
    private int value;

    public BlweeklySchedule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
