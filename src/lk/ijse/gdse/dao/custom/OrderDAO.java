package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Item;
import lk.ijse.gdse.entity.Order;

import java.util.List;

public interface OrderDAO extends CrudDAO<Order,String> {
    public List<Order> searchByText(String text);
}
