import java.util.List;

public interface EmployeeRepository {
    void addEmployee(Employee employee);

    Employee getEmployee(int empId);

    void delete(int employeeId);

    void addUnionMember(int memberId, Employee employee);

    Employee getUnionMember(int memberId);

    void removeUnionMember(int memberId);

    List<Integer> getAllEmployeeIds();
}
