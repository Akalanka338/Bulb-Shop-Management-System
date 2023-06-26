package lk.ijse.gdse.service;

import lk.ijse.gdse.service.custom.Impl.CustomerServiceImpl;
import lk.ijse.gdse.service.custom.Impl.EmployeeServiceImpl;
import lk.ijse.gdse.service.custom.Impl.ItemServiceImpl;
import lk.ijse.gdse.service.custom.Impl.OrderDetailServiceImpl;



public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperService> T getService( ServiceTypes serviceTypes) {
        switch (serviceTypes){
            case CUSTOMER:
                return (T)new CustomerServiceImpl();

            case EMPLOYEE:
                return (T)new EmployeeServiceImpl();

            case ITEM:
                return (T)new ItemServiceImpl();

            case ORDER:
                return (T)new OrderDetailServiceImpl();


            default:
                return null;

        }

    }
}
