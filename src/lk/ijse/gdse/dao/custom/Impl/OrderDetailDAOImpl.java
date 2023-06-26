package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.custom.OrderDetailDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.CartDetail;
import lk.ijse.gdse.entity.OrderDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailDAOImpl implements OrderDetailDAO {


    @Override
    public OrderDetail save(OrderDetail orderDetail) throws ConstraintViolationException {
        try {
            if(DBUtil.executeUpdate("INSERT INTO orderdetail ( Did,item_code,  qty, unitPrice) VALUES (?,?,?,?v)",
                    orderDetail.getDid(),orderDetail.toString(),orderDetail.getQty(),orderDetail.getUnitPrice())){

                return orderDetail;
            }
            throw new SQLException("Failed to save the order details");
        }catch (SQLException e){
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public OrderDetail update(OrderDetail entity) throws ConstraintViolationException {
        return null;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {

    }

    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public Optional<OrderDetail> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String id) {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Customer WHERE Did=?", id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        return 0;
    }

    private final Connection connection;

    public OrderDetailDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OrderDetail> searchByText(String text) {
        return null;
    }


 /*   public static boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!save(cartDetail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(CartDetail cartDetail) throws SQLException {
        String sql = "INSERT INTO Orderdetail VALUES(?, ?, ?, ?)";
        return DBUtil.executeQuery(sql, cartDetail.getOrderId(), cartDetail.getCode(), cartDetail.getQty(),cartDetail.getUnitPrice());
    }
   *//* private static boolean save(CartDetail cartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Orderdetail VALUES(?, ?, ?, ?)";
        return DBUtil.executeQuery(sql, cartDetail.getOrderId(), cartDetail.getCode(), cartDetail.getQty(),cartDetail.getUnitPrice());
    }*//*
*/
}
