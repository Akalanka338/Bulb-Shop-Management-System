package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Employee;

import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    public List<Employee> searchByText(String text);

}
