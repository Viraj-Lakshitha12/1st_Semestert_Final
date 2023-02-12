package lk.ijse.phone.service.custom;

import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.SystemUsersDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface SystemUserService extends SuperService {
    boolean saveSystemUsers(SystemUsersDTO systemUsersDTO) throws SQLException, ClassNotFoundException;
    boolean updateSystemUsers(SystemUsersDTO systemUsersDTO) throws SQLException, ClassNotFoundException;
    boolean deleteSystemUsers(String id) throws SQLException, ClassNotFoundException;
    Optional<SystemUsersDTO> searchSystemUsers(String id) throws SQLException, ClassNotFoundException;
    ArrayList<SystemUsersDTO> getAllSystemUsers() throws SQLException, ClassNotFoundException;
}
