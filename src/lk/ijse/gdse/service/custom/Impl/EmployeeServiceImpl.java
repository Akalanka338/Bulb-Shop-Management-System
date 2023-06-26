package lk.ijse.gdse.service.custom.Impl;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.DaoTypes;
import lk.ijse.gdse.dao.custom.CustomerDAO;
import lk.ijse.gdse.dao.custom.EmployeeDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.EmployeeDTO;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Employee;
import lk.ijse.gdse.service.custom.EmployeeService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.service.exception.InUseException;
import lk.ijse.gdse.service.exception.NotFoundException;
import lk.ijse.gdse.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final Convertor convertor;
    private final Connection connection;


    public EmployeeServiceImpl() {
        connection= DBConnection.getDbConnection().getConnection();
        employeeDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.EMPLOYEE );
        convertor=new Convertor();
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws DuplicateException {
        if (employeeDAO.existByPk(employeeDTO.getEid())) throw new DuplicateException("Employee already saved");

        employeeDAO.save(convertor.toEmployee(employeeDTO));


        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws NotFoundException {
        if (!employeeDAO.existByPk(employeeDTO.getEid())) throw new NotFoundException("Employee not found");

        employeeDAO.update(convertor.toEmployee(employeeDTO));

        return employeeDTO;
    }

    @Override
    public void deleteEmployee(String id) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {
        if (!employeeDAO.existByPk(id)) throw new NotFoundException("employee not found");

        try {
            employeeDAO.existByPk(id);
        }catch (ConstraintViolationException e){
            throw new InUseException("Employee already in used");
        }
    }

    @Override
    public List<EmployeeDTO> findAllEmployee() {
        return employeeDAO.findAll().stream().map(employee -> convertor.fromEmployee(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO findEmployeeById(String id) throws NotFoundException {
        Optional<Employee> optionalEmployee = employeeDAO.findByPk(id);
        if (!optionalEmployee.isPresent()) throw new NotFoundException("Customer not found");

        return convertor.fromEmployee(optionalEmployee.get());
    }

    @Override
    public List<EmployeeDTO> searchEmployeeByText(String text) {
        return employeeDAO.searchByText(text).stream().map(employee -> convertor.fromEmployee(employee)).collect(Collectors.toList());
    }
}
