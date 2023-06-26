package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.service.SuperService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.service.exception.InUseException;
import lk.ijse.gdse.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SuperService {

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException;

    public CustomerDTO updateCustomer(CustomerDTO customerDTO)  throws NotFoundException;

    public void deleteCustomer(String id) throws NotFoundException, InUseException, SQLException,ClassNotFoundException;

    public List<CustomerDTO> findAllCustomers();

    public CustomerDTO findCustomerById(String id) throws NotFoundException;

    public List<CustomerDTO> searchCustomerByText(String text) ;
}

