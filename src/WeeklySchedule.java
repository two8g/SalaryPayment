import java.time.LocalDate;

public class WeeklySchedule implements PaymentSchedule {
    private int value;

    public WeeklySchedule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean isPayDay(LocalDate date) {
        return date.getDayOfWeek().getValue() == value;
    }

    @Override
    public LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return payDate.plusDays(-4);
    }
}
