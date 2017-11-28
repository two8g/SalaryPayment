public interface EmployeeRepository {
    void addEmployee(Employee employee);

    Employee getEmployee(int empId);
}
