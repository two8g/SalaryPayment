import java.time.LocalDate;

public class Paycheck {
    private LocalDate payDate;
    private double grossPay;
    private double deductions;
    private double netPay;
    private String disposition;
    private LocalDate payPeriodStartDate;

    public Paycheck(LocalDate payPeriodStartDate, LocalDate payDate) {
        this.payPeriodStartDate = payPeriodStartDate;
        this.payDate = payDate;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public LocalDate getPayPeriodStartDate() {
        return payPeriodStartDate;
    }
}
