import java.time.LocalDate;

public class SalesReceipt {
    private int amount;
    private LocalDate day;

    public SalesReceipt(LocalDate day, int amount) {
        this.day = day;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getDay() {
        return day;
    }
}
