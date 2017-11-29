import java.time.LocalDate;

public class MonthlySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(LocalDate date) {
        return date.getDayOfMonth() == date.lengthOfMonth();
    }

    @Override
    public LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return payDate.withDayOfMonth(1);
    }
}
