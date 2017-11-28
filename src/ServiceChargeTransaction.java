import java.time.LocalDate;

public class ServiceChargeTransaction implements Transaction {
    private int memberId;
    private LocalDate day;
    private double charge;

    public ServiceChargeTransaction(int memberId, LocalDate day, double charge) {
        this.memberId = memberId;
        this.day = day;
        this.charge = charge;
    }

    @Override
    public void execute() {

    }
}
