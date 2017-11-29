import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
    private static Map<Integer, Integer> memberEmployeeMap = new HashMap<Integer, Integer>();

    private EmployeeRepositoryImpl() {

    }

    public synchronized static EmployeeRepositoryImpl getInstance() {
        return new EmployeeRepositoryImpl();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMap.put(employee.getId(), employee);
    }

    @Override
    public Employee getEmployee(int empId) {
        return employeeMap.get(empId);
    }

    @Override
    public void delete(int employeeId) {
        employeeMap.remove(employeeId);
    }

    @Override
    public void addUnionMember(int memberId, Employee employee) {
        memberEmployeeMap.put(memberId, employee.getId());
    }

    @Override
    public Employee getUnionMember(int memberId) {
        return employeeMap.get(memberEmployeeMap.get(memberId));
    }

    @Override
    public void removeUnionMember(int memberId) {
        memberEmployeeMap.remove(memberId);
    }

    @Override
    public List<Integer> getAllEmployeeIds() {
        List<Integer> employeeIds = new ArrayList<>();
        for (Map.Entry<Integer, Employee> employeeEntry : employeeMap.entrySet()) {
            employeeIds.add(employeeEntry.getKey());
        }
        return employeeIds;
    }
}
