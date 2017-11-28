import java.time.LocalDate;

public class SalesReceiptTransaction implements Transaction {
    private LocalDate day;
    private int i;
    private int employeeId;

    public SalesReceiptTransaction(LocalDate day, int i, int employeeId) {
        this.day = day;
        this.i = i;

        this.employeeId = employeeId;
    }

    @Override
    public void execute() {

    }
}
