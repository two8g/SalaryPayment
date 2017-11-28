import java.time.LocalDate;

public class TimeCardTransaction implements Transaction {
    private LocalDate day;
    private double hours;
    private int employeeId;

    public TimeCardTransaction(LocalDate day, double hours, int employeeId) {
        this.day = day;
        this.hours = hours;
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {

    }
}
