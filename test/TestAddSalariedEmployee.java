import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TestAddSalariedEmployee {
    private EmployeeRepository employeeRepository = EmployeeRepositoryImpl.getInstance();

    /**
     * 增加钟点工
     */
    @Test
    public void should_add_hourly_employee() {
        //give
        int employeeId = 1;
        String name = "Bob";
        String address = "Home";
        double hourSalary = 50.00;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(employeeId, name, address, hourSalary);
        //when
        addHourlyEmployee.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeId, employee.getId());
        Assert.assertEquals(name, employee.getName());
        Assert.assertEquals(address, employee.getAddress());
        Assert.assertTrue(employee.getPaymentClassification() instanceof HourlyClassification);
        Assert.assertEquals(hourSalary, ((HourlyClassification) employee.getPaymentClassification()).getHourlySalary(), 0.01);
        Assert.assertTrue(employee.getPaymentSchedule() instanceof WeeklySchedule);
        Assert.assertEquals(5, ((WeeklySchedule) employee.getPaymentSchedule()).getValue());
    }

    /**
     * 增加带薪雇员
     */
    @Test
    public void should_add_salaried_employee() {
        //give
        int employeeId = 1;
        String name = "name";
        String address = "address";
        double salary = 1000.00;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(employeeId, name, address, salary);
        //when
        addSalariedEmployee.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeId, employee.getId());
        Assert.assertEquals(name, employee.getName());
        Assert.assertEquals(address, employee.getAddress());
        Assert.assertTrue(employee.getPaymentClassification() instanceof SalariedClassification);
        Assert.assertEquals(salary, ((SalariedClassification) employee.getPaymentClassification()).getSalary(), 0.01);
        Assert.assertTrue(employee.getPaymentSchedule() instanceof MonthlySchedule);
    }

    /**
     * 增加带薪销售雇员
     */
    @Test
    public void should_add_commissioned_employee() {
        //give
        int employeeId = 1;
        String name = "John";
        String address = "Street A";
        double salary = 500.00;
        double commissionRate = 100.00;
        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(employeeId, name, address, salary, commissionRate);
        //when
        addCommissionedEmployee.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeId, employee.getId());
        Assert.assertEquals(name, employee.getName());
        Assert.assertEquals(address, employee.getAddress());
        Assert.assertTrue(employee.getPaymentClassification() instanceof CommissionedClassification);
        Assert.assertEquals(salary, ((CommissionedClassification) employee.getPaymentClassification()).getSalary(), 0.01);
        Assert.assertEquals(commissionRate, ((CommissionedClassification) employee.getPaymentClassification()).getCommissionRate(), 0.01);
        Assert.assertTrue(employee.getPaymentSchedule() instanceof BlweeklySchedule);
        Assert.assertEquals(5, ((BlweeklySchedule) employee.getPaymentSchedule()).getValue());
    }

    /**
     * 删除雇员
     */
    @Test
    public void should_delete_employee() {
        //give
        int employeeId = 3;
        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(employeeId, "name", "address", 100.00, 10.50);
        addCommissionedEmployee.execute();
        //when
        {
            Employee employee = employeeRepository.getEmployee(employeeId);
            Assert.assertNotNull(employee);
        }
        DeleteEmployeeTransaction deleteEmployeeTransaction = new DeleteEmployeeTransaction(employeeId);
        deleteEmployeeTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNull(employee);
    }

    /**
     * 登记时间卡
     */
    @Test
    public void should_add_time_card() {
        //give
        int employeeId = 1;
        new AddHourlyEmployee(employeeId, "name", "address", 50.00).execute();
        LocalDate day = LocalDate.of(2017, 11, 20);
        //when
        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(day, 8.0, employeeId);
        timeCardTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        Assert.assertTrue(paymentClassification instanceof HourlyClassification);
        TimeCard timeCard = ((HourlyClassification) paymentClassification).getTimeCard(day);
        Assert.assertNotNull(timeCard);
        Assert.assertEquals(8.0, timeCard.getHours(), 0.01);
    }

    /**
     * 登记销售凭条
     */
    @Test
    public void should_add_sales_receipt() {
        //give
        int employeeId = 1;
        new AddCommissionedEmployee(employeeId, "name", "address", 1000.00, 100.50).execute();
        LocalDate day = LocalDate.of(2017, 11, 20);
        //when
        SalesReceiptTransaction salesReceiptTransaction = new SalesReceiptTransaction(day, 5, employeeId);
        salesReceiptTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        Assert.assertTrue(paymentClassification instanceof CommissionedClassification);
        SalesReceipt salesReceipt = ((CommissionedClassification) paymentClassification).getSalesReceipt(day);
        Assert.assertNotNull(salesReceipt);
        Assert.assertEquals(5, salesReceipt.getAmount());
    }

    /**
     * 登记服务费用
     */
    @Test
    public void should_add_service_charge() {
        //give
        int employeeId = 2;
        int memberId = 10;
        new AddHourlyEmployee(employeeId, "name", "address", 50.3).execute();
        Employee employee = employeeRepository.getEmployee(employeeId);
        //when
        UnionAffiliation unionAffiliation = new UnionAffiliation(memberId, 12.5);
        employee.setAffiliation(unionAffiliation);
        employeeRepository.addUnionMember(memberId, employee);
        LocalDate day = LocalDate.of(2017, 11, 1);
        ServiceChargeTransaction serviceChargeTransaction = new ServiceChargeTransaction(memberId, day, 12.95);
        serviceChargeTransaction.execute();
        //then
        Assert.assertNotNull(employee);
        ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(day);
        Assert.assertNotNull(serviceCharge);
        Assert.assertEquals(12.95, serviceCharge.getAmount(), 0.01);
    }

    /**
     * 修改雇员属性name
     */
    @Test
    public void should_change_employee_name() {
        //give
        int employeeId = 4;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(employeeId, "Jake", "House", 60.0);
        addHourlyEmployee.execute();
        //when
        ChangeNameTransaction changeNameTransaction = new ChangeNameTransaction(employeeId, "Frank");
        changeNameTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals("Frank", employee.getName());
    }

    /**
     * 修改雇员类别
     */
    @Test
    public void should_change_employee_classification() {
        //give
        int employeeId = 3;
        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(employeeId, "name", "address", 1000.00, 200.00);
        addCommissionedEmployee.execute();
        //when
        ChangeHourlyTransaction changeHourlyTransaction = new ChangeHourlyTransaction(employeeId, 80.73);
        changeHourlyTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeId, employee.getId());
        Assert.assertTrue(employee.getPaymentClassification() instanceof HourlyClassification);
        Assert.assertEquals(80.73, ((HourlyClassification) employee.getPaymentClassification()).getHourlySalary(), 0.01);
        Assert.assertTrue(employee.getPaymentSchedule() instanceof WeeklySchedule);
        Assert.assertEquals(5, ((WeeklySchedule) employee.getPaymentSchedule()).getValue());
    }

    /**
     * 修改雇员支付方式
     */
    @Test
    public void should_change_employee_payment_method() {
        //give
        int employeeId = 1;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(employeeId, "name", "address", 50.34);
        addHourlyEmployee.execute();
        //when
        String mail = "mail@mail.com";
        ChangeMailTransaction changeMailTransaction = new ChangeMailTransaction(employeeId, mail);
        changeMailTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertTrue(employee.getPaymentMethod() instanceof MailMethod);
        Assert.assertEquals(mail, ((MailMethod) employee.getPaymentMethod()).getMailAddress());
    }

    /**
     * 修改雇员协会属性
     */
    @Test
    public void should_change_employee_affiliation() {
        //give
        int employeeId = 2;
        int memberId = 47;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(employeeId, "name", "address", 35.00);
        addHourlyEmployee.execute();
        //when
        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(employeeId, memberId, 39.00);
        changeMemberTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Affiliation affiliation = employee.getAffiliation();
        Assert.assertNotNull(affiliation);
        Assert.assertTrue(affiliation instanceof UnionAffiliation);
        UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
        Assert.assertEquals(39.00, unionAffiliation.getWeeklyCharge(), 0.01);
        Employee member = employeeRepository.getUnionMember(memberId);
        Assert.assertNotNull(member);
        Assert.assertSame(employee, member);
    }

    /**
     * 删除协会成员
     */
    @Test
    public void should_remove_union_member() {
        //give
        int employeeId = 2;
        int memberId = 47;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(employeeId, "name", "address", 35.00);
        addHourlyEmployee.execute();
        Employee employee = employeeRepository.getEmployee(employeeId);
        UnionAffiliation unionAffiliation = new UnionAffiliation(memberId, employeeId);
        employee.setAffiliation(unionAffiliation);
        employeeRepository.addUnionMember(memberId, employee);
        //when
        ChangeUnffiliatedTransaction changeUnffiliatedTransaction = new ChangeUnffiliatedTransaction(employeeId);
        changeUnffiliatedTransaction.execute();
        //then
        Assert.assertTrue(employee.getAffiliation() instanceof NoAffiliation);
        Assert.assertNull(employeeRepository.getUnionMember(memberId));
    }
}
