package lk.ijse.gdse.service.custom.Impl;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.DaoTypes;
import lk.ijse.gdse.dao.custom.CustomerDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.service.custom.CustomerService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.service.exception.InUseException;
import lk.ijse.gdse.service.exception.NotFoundException;
import lk.ijse.gdse.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;
    private final Convertor convertor;
    private final Connection connection;


    public CustomerServiceImpl() {
        connection= DBConnection.getDbConnection().getConnection();
        customerDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.CUSTOMER );
        convertor=new Convertor();
    }


    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException {
        if (customerDAO.existByPk(customerDTO.getId())) throw new DuplicateException("Customer already saved");

        customerDAO.save(convertor.toCustomer(customerDTO));

        return customerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) throws NotFoundException {
        if (!customerDAO.existByPk(customerDTO.getId())) throw new NotFoundException("Customer not found");

        customerDAO.update(convertor.toCustomer(customerDTO));

        return customerDTO;
    }

    @Override
    public void deleteCustomer(String id) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {
        if (!customerDAO.existByPk(id)) throw new NotFoundException("Customer not found");

        try {
            customerDAO.existByPk(id);
        }catch (ConstraintViolationException e){
            throw new InUseException("Customer already in used");
        }
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return customerDAO.findAll().stream().map(customer -> convertor.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(String id) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerDAO.findByPk(id);
        if (!optionalCustomer.isPresent()) throw new NotFoundException("Customer not found");

        return convertor.fromCustomer(optionalCustomer.get());
    }

    @Override
    public List<CustomerDTO> searchCustomerByText(String text) {
        return customerDAO.searchByText(text).stream().map(customer -> convertor.fromCustomer(customer)).collect(Collectors.toList());
    }
}
