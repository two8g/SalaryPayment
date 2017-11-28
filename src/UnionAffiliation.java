import java.time.LocalDate;

public class UnionAffiliation implements Affiliation {
    private int memberId;
    private double weeklyCharge;

    public UnionAffiliation(int memberId, double weeklyCharge) {
        this.memberId = memberId;
        this.weeklyCharge = weeklyCharge;
    }

    public ServiceCharge getServiceCharge(LocalDate day) {
        return null;
    }
}
