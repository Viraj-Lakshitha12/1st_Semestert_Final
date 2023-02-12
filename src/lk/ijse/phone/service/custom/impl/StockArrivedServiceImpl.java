package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.StockArrievdDAO;
import lk.ijse.phone.dao.custom.impl.StockArrivedDAOImpl;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.entity.StockArrived;
import lk.ijse.phone.service.custom.StockArrivedService;
import lk.ijse.phone.dto.StockArrievdDTO;
import lk.ijse.phone.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockArrivedServiceImpl implements StockArrivedService {
    private final StockArrievdDAO stockArrievdDAO = (StockArrievdDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.STOCK_ARRIVED);
    private final Convertor convertor = new Convertor();

    @Override
    public boolean saveStockArrived(StockArrievdDTO stockArrievdDTO) throws SQLException, ClassNotFoundException {
        return stockArrievdDAO.save(convertor.toStockArrived(stockArrievdDTO));
    }
    @Override
    public boolean updateStockArrived(StockArrievdDTO stockArrievdDTO) throws SQLException, ClassNotFoundException {
        return stockArrievdDAO.update(convertor.toStockArrived(stockArrievdDTO));
    }
    @Override
    public boolean deleteStockArrived(String date) throws SQLException, ClassNotFoundException {
        return stockArrievdDAO.deleteByPk(date);
    }
    @Override
    public Optional<StockArrievdDTO> searchStockArrived(String date) throws SQLException, ClassNotFoundException {
        Optional<StockArrived> optionalStockArrived = stockArrievdDAO.findByPk(date);
        if (optionalStockArrived.isPresent()){
            return Optional.ofNullable(convertor.fromStockArrived(optionalStockArrived.get()));
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<StockArrievdDTO> GetAllStockArrivedData() throws SQLException, ClassNotFoundException {
        return (ArrayList<StockArrievdDTO>) stockArrievdDAO.getAll().stream().map(stockArrived -> convertor.fromStockArrived(stockArrived)).collect(Collectors.toList());
    }
}
