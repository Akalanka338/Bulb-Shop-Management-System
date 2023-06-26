package lk.ijse.gdse.dao;

import lk.ijse.gdse.dao.custom.Impl.*;

import java.sql.Connection;

public class DaoFactory {
    private static DaoFactory daoFactory ;

    private DaoFactory() {
    }

    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }

    public <T extends SuperDAO> T getDAO(Connection connection, DaoTypes daoType) {
        switch (daoType){
            case CUSTOMER:
                return (T)new CustomerDAOImpl(connection);

            case EMPLOYEE:
                return (T)new EmployeeDAOImpl(connection);

            case ITEM:
                return (T)new ItemDAOImpl(connection);


            case ORDER:
                return (T) new OrderDAOImpl(connection);

            case ORDER_DETAIL:
                return (T)new OrderDetailDAOImpl(connection);

            case PLACE_ORDER:
                return (T)new PlaceOrderDAOImpl(connection);

            default:
                return null;

        }

    }
}
