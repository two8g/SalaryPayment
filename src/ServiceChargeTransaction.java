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
        Employee employee = EmployeeRepositoryImpl.getInstance().getUnionMember(memberId);
        if(employee.getAffiliation() instanceof UnionAffiliation){
            UnionAffiliation unionAffiliation = (UnionAffiliation) employee.getAffiliation();
            if (unionAffiliation.getMemberId() == memberId) {
                unionAffiliation.addServiceCharge(new ServiceCharge(day, charge));
            }
        }
    }
}
