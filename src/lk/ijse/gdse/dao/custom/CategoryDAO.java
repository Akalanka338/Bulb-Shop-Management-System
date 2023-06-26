package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Brand;
import lk.ijse.gdse.entity.Category;

import java.util.List;

public interface CategoryDAO extends CrudDAO<Category,String> {
    public List<Category> searchByText(String text);
}
