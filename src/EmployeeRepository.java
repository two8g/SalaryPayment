public interface EmployeeRepository {
    void addEmployee(Employee employee);

    Employee getEmp(int empId);
}
