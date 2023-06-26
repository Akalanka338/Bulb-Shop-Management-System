package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.custom.EmployeeDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;

    }



    @Override
    public Employee save(Employee employee) throws ConstraintViolationException {

        try {
            if(DBUtil.executeUpdate("INSERT INTO employee (eid, fname,lname,role,tel_num,dob) VALUES (?,?,?,?,?,?)",
                    employee.getEid(),employee.getFirst_name(),employee.getLast_name(),employee.getRole(),employee.getTelephone(),employee.getDob())){
                return employee;
            }
            throw new SQLException("Failed to save the employee");
        }catch (SQLException e){
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public Employee update(Employee employee) throws ConstraintViolationException {
        try {
            if(DBUtil.executeUpdate("UPDATE Employee SET fname=? ,lname=? ,role=?, tel_num=?,dob=? WHERE eid=?",employee.getFirst_name(),employee.getLast_name(),employee.getRole(),employee.getTelephone(),employee.getDob(),employee.getEid())){
                return employee;
            }
            throw new SQLException("Failed to update the Employee");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public void deleteByPk(String id) throws ConstraintViolationException {
        try {
            if(!DBUtil.executeUpdate("DELETE FROM Employee WHERE eid=?",id)) throw new SQLException("Failed to delete the employee");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Employee> findAll() {
        try{
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Employee");
            return getEmoployeeList(rst);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load the employee");
        }
    }

    @Override
    public Optional<Employee> findByPk(String id) {
        try{
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Employee WHERE cid=?", id);
            if(rst.next()){
                return Optional.of(new Employee(rst.getString("eid"), rst.getString("fname"), rst.getString("lname"), rst.getString("role"),rst.getString("tel_num"), rst.getString("dob")));

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the employee details");
        }
    }

    @Override
    public boolean existByPk(String id) {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Employee WHERE eid=?", id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(eid) AS count FROM Employee");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> searchByText(String text) {
        try{
            text="%"+text+"%";
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Employee WHERE eid LIKE ? OR fname LIKE ? OR lname LIKE ? ", text, text, text);
            return getEmoployeeList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Employee> getEmoployeeList(ResultSet rst)  {
        try {
            List<Employee> employeeList= new ArrayList<>();
            while (rst.next()){
                Employee employee = new Employee(rst.getString("eid"), rst.getString("fname"), rst.getString("lname"), rst.getString("role"),rst.getString("tel_num"), rst.getString("dob"));

                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
