package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Customer;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public List<Customer> searchByText(String text);
}
