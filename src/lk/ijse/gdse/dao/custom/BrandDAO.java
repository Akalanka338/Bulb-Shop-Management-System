package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Brand;
import lk.ijse.gdse.entity.Customer;

import java.util.List;

public interface BrandDAO extends CrudDAO<Brand,String> {
    public List<Brand> searchByText(String text);
}
