package lk.ijse.gdse.dao.custom;

import lk.ijse.gdse.dao.CrudDAO;
import lk.ijse.gdse.entity.Item;
import lk.ijse.gdse.entity.PlaceOrder;

import java.util.List;

public interface PlaceOrderDAO extends CrudDAO<PlaceOrder,String> {
    public List<PlaceOrder> searchByText(String text);
}
