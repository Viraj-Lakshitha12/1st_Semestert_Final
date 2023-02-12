package lk.ijse.phone.service;

import lk.ijse.phone.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getServiceFactory(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public static SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case STOCK:
                return new StockServiceImpl();
            case CUSTOMER:
                return new CustomerServiceImpl();
            case EMPLOYEE:
                return new EmployeeServiceImpl();
            case PLACES:
                return new PlaceServiceImpl();
            case STOCK_ARRIVED:
                return new StockArrivedServiceImpl();
            case SUPPLIER:
                return new SupplierServiceImpl();
            case SYSTEM_USERS:
                return new SystemUserServiceImpl();
            case HR:
                return new HrServiceImpl();
            default:
                return null;
        }

    }
}
