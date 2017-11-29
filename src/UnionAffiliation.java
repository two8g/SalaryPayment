import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UnionAffiliation implements Affiliation {
    private int memberId;
    private double weeklyCharge;
    private List<ServiceCharge> serviceCharges = new ArrayList<>();
    private static final int CHARGE_DAY_OF_WEEK = 5;

    public UnionAffiliation(int memberId, double weeklyCharge) {
        this.memberId = memberId;
        this.weeklyCharge = weeklyCharge;
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {
        serviceCharges.add(serviceCharge);
    }

    public int getMemberId() {
        return memberId;
    }

    public double getWeeklyCharge() {
        return weeklyCharge;
    }

    public ServiceCharge getServiceCharge(LocalDate day) {
        for (ServiceCharge serviceCharge : serviceCharges) {
            if (day.equals(serviceCharge.getDate())) {
                return serviceCharge;
            }
        }
        return null;
    }

    public double calculateDeductions(Paycheck paycheck) {
        double deduce = numberOfChargeInPayPeriod(paycheck.getPayPeriodStartDate(), paycheck.getPayDate()) * weeklyCharge;
        for (ServiceCharge serviceCharge : serviceCharges) {
            if (Util.inPeriod(serviceCharge.getDate(), paycheck.getPayPeriodStartDate(), paycheck.getPayDate())) {
                deduce += serviceCharge.getAmount();
            }
        }
        return deduce;
    }

    private int numberOfChargeInPayPeriod(LocalDate start, LocalDate end) {
        int days = Period.between(start, end).getDays() + 1;
        int startDayOfWeek = start.getDayOfWeek().getValue();
        return Util.numberDayOfWeekInDays(CHARGE_DAY_OF_WEEK, startDayOfWeek, days);
    }
}
