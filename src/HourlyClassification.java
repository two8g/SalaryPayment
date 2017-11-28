import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HourlyClassification extends PaymentClassification {
    public static final int FRIDAY = 5;
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
}
