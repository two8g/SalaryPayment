import java.time.LocalDate;

public class BlweeklySchedule implements PaymentSchedule {
    private int value;

    public BlweeklySchedule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean isPayDay(LocalDate date) {
        return date.getDayOfWeek().getValue() == value;
    }

    @Override
    public LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return payDate.plusDays(-4 - 7);
    }
}
