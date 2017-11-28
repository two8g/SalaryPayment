import org.junit.Assert;
import org.junit.Test;

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
}
