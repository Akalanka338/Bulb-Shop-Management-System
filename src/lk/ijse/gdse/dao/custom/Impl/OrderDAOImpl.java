package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.custom.OrderDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {

    private final Connection connection;

    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Order save(Order order) throws ConstraintViolationException {
       return null;
    }

    @Override
    public Order update(Order entity) throws ConstraintViolationException {
        return null;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {

    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Order> searchByText(String text) {
        return null;
    }

    public Order save1(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?)";
        return (Order) DBUtil.executeQuery(sql, order.getOrderId(), order.getDate(), order.getCustomerId());
    }
    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT oid FROM Orders ORDER BY oid DESC LIMIT 1";
        ResultSet result = DBUtil.executeQuery(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {

        /*if (currentOrderId != null) {
            String[] split = currentOrderId.split("B0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "I0" + id;
        }*/
            return "D03";


    }

}
