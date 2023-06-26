package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.OrderDetail;
import lk.ijse.gdse.service.SuperService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.service.exception.InUseException;
import lk.ijse.gdse.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailService extends SuperService {
    public OrderDetail saveOrder(OrderDetail customerDTO) throws DuplicateException;

    public OrderDetail updateOrder(OrderDetail customerDTO)  throws NotFoundException;

    public void deleteOrder(String id) throws NotFoundException, InUseException, SQLException,ClassNotFoundException;

    public List<OrderDetail> findAllOrders();

    public OrderDetail findOrderById(String id) throws NotFoundException;

    public List<OrderDetail> searchOrderByText(String text) ;
}
