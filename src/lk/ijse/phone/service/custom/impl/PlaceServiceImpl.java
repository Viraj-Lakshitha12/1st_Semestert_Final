package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.PlaceDAO;
import lk.ijse.phone.dao.custom.impl.PlaceDAOImpl;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.entity.RackDetails;
import lk.ijse.phone.service.custom.PlaceService;
import lk.ijse.phone.dto.RackDetailsDTO;
import lk.ijse.phone.service.util.Convertor;
import org.apache.batik.svggen.font.table.Coverage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlaceServiceImpl implements PlaceService {
    private final PlaceDAO placeDAO = (PlaceDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.PLACES);
    private final Convertor convertor = new Convertor();
    @Override
    public boolean savePlace(RackDetailsDTO rackDetailsDTO) throws ClassNotFoundException, SQLException {
        return placeDAO.save(convertor.toRackDetails(rackDetailsDTO));
    }

    @Override
    public Optional<RackDetailsDTO> searchPlace(String rackNumber) throws SQLException, ClassNotFoundException {
        Optional<RackDetails> rackDetails = placeDAO.findByPk(rackNumber);
        if (rackDetails.isPresent()){
            return Optional.ofNullable(convertor.fromRackDetails(rackDetails.get()));
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<RackDetailsDTO> loadAllPlaces() throws SQLException, ClassNotFoundException {
        return (ArrayList<RackDetailsDTO>) placeDAO.getAll().stream().map(rackDetails -> convertor.fromRackDetails(rackDetails)).collect(Collectors.toList());
    }

    @Override
    public boolean updatePlace(RackDetailsDTO rackDetailsDTO) throws SQLException, ClassNotFoundException {
        return placeDAO.update(convertor.toRackDetails(rackDetailsDTO));
    }

    @Override
    public boolean deletePlace(String rackNumber) throws SQLException, ClassNotFoundException {
        return placeDAO.deleteByPk(rackNumber);
    }
}
