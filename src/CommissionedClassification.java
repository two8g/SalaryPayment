import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommissionedClassification implements PaymentClassification {
    private double salary;
    private double commissionRate;
    private List<SalesReceipt> salesReceipts;

    public CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
        this.salesReceipts = new ArrayList<>();
    }

    public double getSalary() {
        return salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public SalesReceipt getSalesReceipt(LocalDate day) {
        for (SalesReceipt salesReceipt : salesReceipts) {
            if (day.equals(salesReceipt.getDay())) {
                return salesReceipt;
            }
        }
        return null;
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceipts.add(salesReceipt);
    }

    @Override
    public double calculatePay(Paycheck paycheck) {
        return 0;
    }

}
