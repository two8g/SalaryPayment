import org.junit.Assert;
import org.junit.Test;

public class TestAddSalariedEmployee {
    private EmployeeRepository employeeRepository = EmployeeRepositoryImpl.getInstance();

    @Test
    public void should_add_hour_emp() {
        //give
        int empId = 1;
        String name = "name";
        String address = "address";
        double salary = 1000.00;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, name, address, salary);
        //when
        addSalariedEmployee.execute();
        //then
        Employee employee = employeeRepository.getEmp(empId);
        Assert.assertEquals(empId, employee.getId());
        Assert.assertEquals(name, employee.getName());
        Assert.assertEquals(address, employee.getAddress());
        Assert.assertTrue(employee.getPaymentClassification() instanceof SalariedClassification);
        Assert.assertEquals(salary, ((SalariedClassification) employee.getPaymentClassification()).getSalary(), 0.01);
        Assert.assertTrue(employee.getPaymentSchedule() instanceof MonthlySchedule);
    }
}
