package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Employee;
import lk.ijse.gdse.entity.Item;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
    public List<Item> searchByText(String text);
}
