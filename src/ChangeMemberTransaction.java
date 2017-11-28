public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    private int memberId;
    private double charge;

    public ChangeMemberTransaction(int employeeId, int memberId, double charge) {
        super(employeeId);
        this.memberId = memberId;
        this.charge = charge;
    }

    @Override
    Affiliation getAffiliation() {
        return new UnionAffiliation(memberId, charge);
    }

    @Override
    void recordMembership(Employee employee) {
        EmployeeRepositoryImpl.getInstance().addUnionMember(memberId, employee);
    }
}
