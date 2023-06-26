package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.custom.PlaceOrderDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.entity.Order;
import lk.ijse.gdse.entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {

    private final Connection connection;
    public PlaceOrderDAOImpl(Connection connection) {

        this.connection = connection;

    }

    @Override
    public PlaceOrder save(PlaceOrder entity) throws ConstraintViolationException {
        return null;
    }

    @Override
    public PlaceOrder update(PlaceOrder entity) throws ConstraintViolationException {
        return null;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {

    }

    @Override
    public List<PlaceOrder> findAll() {
        return null;
    }

    @Override
    public Optional<PlaceOrder> findByPk(String pk) {
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
    public List<PlaceOrder> searchByText(String text) {
        return null;
    }
    /*public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean isOrderAdded = OrderDAOImpl(new Order(placeOrder.getCode(), LocalDate.now(), placeOrde);
            System.out.println(isOrderAdded);
            if (isOrderAdded) {
                boolean isOrderDetailAdded = OrderDetailDAOImpl.saveOrderDetails(placeOrder.getOrderDetails());

                //System.out.println("QTY"+" "+isUpdated);
                if (isOrderDetailAdded) {
                    boolean isUpdated = ItemDAOImpl.updateQty(placeOrder.getOrderDetails());
                    if (isUpdated) {
                        DBConnection.getDbConnection().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getDbConnection().getConnection().rollback();
            return false;
//            return  true;
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }*/

}
