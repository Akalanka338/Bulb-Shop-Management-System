package lk.ijse.gdse.service.custom.Impl;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.DaoTypes;
import lk.ijse.gdse.dao.custom.ItemDAO;
import lk.ijse.gdse.dao.custom.OrderDetailDAO;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.OrderDetail;
import lk.ijse.gdse.service.custom.OrderDetailService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.service.exception.InUseException;
import lk.ijse.gdse.service.exception.NotFoundException;
import lk.ijse.gdse.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailDAO orderDetailDAO;
    private final Convertor convertor;
    private final Connection connection;

    public OrderDetailServiceImpl() {

        connection= DBConnection.getDbConnection().getConnection();
        orderDetailDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.ORDER_DETAIL );
        convertor=new Convertor();
    }


    @Override
    public OrderDetail saveOrder(OrderDetail orderDetail) throws DuplicateException {
        if (orderDetailDAO.existByPk(orderDetail.getDid())) throw new DuplicateException("Order already saved");

        orderDetailDAO.save(convertor.toOrder(orderDetail));

        return orderDetail;
    }

    @Override
    public OrderDetail updateOrder(OrderDetail customerDTO) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteOrder(String id) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {

    }

    @Override
    public List<OrderDetail> findAllOrders() {
        return null;
    }

    @Override
    public OrderDetail findOrderById(String id) throws NotFoundException {
        return null;
    }

    @Override
    public List<OrderDetail> searchOrderByText(String text) {
        return null;
    }
}
