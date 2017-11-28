import java.time.LocalDate;

public class TimeCard {

    private double hours;
    private LocalDate day;

    public TimeCard(LocalDate day, double hours) {
        this.hours = hours;
        this.day = day;
    }

    public double getHours() {
        return hours;
    }

    public LocalDate getDay() {
        return day;
    }
}
