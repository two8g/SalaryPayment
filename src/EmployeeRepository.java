public interface EmployeeRepository {
    void addEmployee(Employee employee);

    Employee getEmployee(int empId);

    void delete(int employeeId);
}
