import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HourlyClassification implements PaymentClassification {
    public static final int FRIDAY = 5;
    private static final double WORK_HOUR_OF_DAY = 8.0;
    private static final double MORE_PAY_RATE = 1.5;
    private double hourlySalary;
    private List<TimeCard> timeCards;

    public HourlyClassification(double hourlySalary) {
        this.hourlySalary = hourlySalary;
        timeCards = new ArrayList<>();
    }

    public double getHourlySalary() {
        return hourlySalary;
    }

    public TimeCard getTimeCard(LocalDate day) {
        for (TimeCard timeCard : timeCards) {
            if (timeCard.getDay().equals(day)) {
                return timeCard;
            }
        }
        return null;
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCards.add(timeCard);
    }

    @Override
    public double calculatePay(Paycheck paycheck) {
        double pay = 0.0;
        for (TimeCard timeCard : timeCards) {
            if (isInPayPeriods(timeCard, paycheck.getPayDate())) {
                pay += calculatePayForTimeCard(timeCard);
            }
        }
        return pay;
    }

    private double calculatePayForTimeCard(TimeCard timeCard) {
        double hours = timeCard.getHours();
        double overTime = Math.max(0.0, hours - WORK_HOUR_OF_DAY);
        double straightTime = hours - overTime;
        return straightTime * hourlySalary + overTime * hourlySalary * MORE_PAY_RATE;
    }

    private boolean isInPayPeriods(TimeCard timeCard, LocalDate payDay) {
        LocalDate startDay = payDay.plusDays(-5);
        return Util.inPeriod(timeCard.getDay(), startDay, payDay);
    }

}
