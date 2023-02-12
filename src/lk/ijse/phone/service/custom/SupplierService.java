package lk.ijse.phone.service.custom;

import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface SupplierService extends SuperService {
    boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException;
    Optional<SupplierDTO> searchSupplier(String supplierId) throws SQLException, ClassNotFoundException;
    ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;
}
