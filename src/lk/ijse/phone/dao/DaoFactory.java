package lk.ijse.phone.dao;

import lk.ijse.phone.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory ;
    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }

    public  SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case ORDERS:
                return new OrderDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PAYMENT_DETAILS:
                return new PaymentDetailsDAOImpl();
            case PENDING_ORDERS:
                return new PendingOrderDAOImpl();
            case PLACES:
                return new PlaceDAOImpl();
            case STOCK_ARRIVED:
                return new StockArrivedDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SYSTEM_USERS:
                return new SystemUsersDAOImpl();
            case HR:
                return new HrDAOImpl();
            default:
                return null;
        }
    }
}
