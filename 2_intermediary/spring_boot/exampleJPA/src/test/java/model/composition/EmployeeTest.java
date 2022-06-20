package model.composition;

import com.guilhermepalma.exampleJPA.model.DAO.DAO;
import com.guilhermepalma.exampleJPA.model.composition.Address;
import com.guilhermepalma.exampleJPA.model.composition.Employee;

public class EmployeeTest {

    private static DAO<Employee> employeeDAO;

    public static void main(String[] args) {
        employeeDAO = new DAO<>(Employee.class);

        insertEmployee();

        employeeDAO.close();
    }

    private static void insertEmployee() {
        Address address = new Address("São Paulo - Brasil");
        Employee employee = new Employee("Joao", address);

        employeeDAO.registerAtomic(employee);
    }
}
