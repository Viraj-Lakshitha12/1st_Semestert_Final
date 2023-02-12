package lk.ijse.phone.service.custom;

import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.StockArrievdDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface StockArrivedService extends SuperService {
    boolean saveStockArrived(StockArrievdDTO stockArrievdDTO) throws SQLException, ClassNotFoundException;
    boolean updateStockArrived(StockArrievdDTO stockArrievdDTO) throws SQLException, ClassNotFoundException;
    boolean deleteStockArrived(String date) throws SQLException, ClassNotFoundException;
    Optional<StockArrievdDTO> searchStockArrived(String date) throws SQLException, ClassNotFoundException;
    ArrayList<StockArrievdDTO> GetAllStockArrivedData() throws SQLException, ClassNotFoundException;
}
