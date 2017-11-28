import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UnionAffiliation implements Affiliation {
    public int getMemberId() {
        return memberId;
    }

    public double getWeeklyCharge() {
        return weeklyCharge;
    }

    private int memberId;
    private double weeklyCharge;
    private List<ServiceCharge> serviceCharges= new ArrayList<>();

    public UnionAffiliation(int memberId, double weeklyCharge) {
        this.memberId = memberId;
        this.weeklyCharge = weeklyCharge;
    }

    public void addServiceCharge(ServiceCharge serviceCharge){
        serviceCharges.add(serviceCharge);
    }

    public ServiceCharge getServiceCharge(LocalDate day) {
        for (ServiceCharge serviceCharge : serviceCharges) {
            if(day.equals(serviceCharge.getDate())){
                return serviceCharge;
            }
        }
        return null;
    }
}
