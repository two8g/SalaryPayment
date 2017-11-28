import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommissionedClassification extends PaymentClassification {
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

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
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
}
