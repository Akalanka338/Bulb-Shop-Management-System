package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.EmployeeDTO;
import lk.ijse.gdse.service.SuperService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.service.exception.InUseException;
import lk.ijse.gdse.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends SuperService {
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws DuplicateException;

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO)  throws NotFoundException;

    public void deleteEmployee(String id) throws NotFoundException, InUseException, SQLException,ClassNotFoundException;

    public List<EmployeeDTO> findAllEmployee();

    public EmployeeDTO findEmployeeById(String id) throws NotFoundException;

    public List<EmployeeDTO> searchEmployeeByText(String text) ;
}
