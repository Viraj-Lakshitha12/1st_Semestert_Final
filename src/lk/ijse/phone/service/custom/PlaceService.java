package lk.ijse.phone.service.custom;

import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.RackDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface PlaceService extends SuperService {
    boolean savePlace(RackDetailsDTO rackDetailsDTO) throws SQLException, ClassNotFoundException;
    Optional<RackDetailsDTO> searchPlace(String rackNumber) throws SQLException, ClassNotFoundException;
    ArrayList<RackDetailsDTO> loadAllPlaces() throws SQLException, ClassNotFoundException;
    boolean updatePlace(RackDetailsDTO rackDetailsDTO) throws SQLException, ClassNotFoundException;
    boolean deletePlace(String rackNumber) throws SQLException, ClassNotFoundException;
}
