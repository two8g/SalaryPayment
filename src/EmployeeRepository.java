public interface EmployeeRepository {
    void addEmployee(Employee employee);

    Employee getEmployee(int empId);

    void delete(int employeeId);

    void addUnionMember(int memberId, Employee employee);
}
