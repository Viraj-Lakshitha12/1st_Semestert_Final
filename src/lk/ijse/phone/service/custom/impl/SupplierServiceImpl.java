package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.SupplierDAO;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.entity.Supplier;
import lk.ijse.phone.service.custom.SupplierService;
import lk.ijse.phone.dto.SupplierDTO;
import lk.ijse.phone.service.util.Convertor;
import org.apache.hadoop.hdfs.web.resources.GetOpParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class SupplierServiceImpl implements SupplierService {
    private final SupplierDAO supplierDAO = (SupplierDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.SUPPLIER);
    private final Convertor convertor = new Convertor();

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(convertor.toSupplier(supplierDTO));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
       return supplierDAO.update(convertor.toSupplier(supplierDTO));
    }

    @Override
    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.deleteByPk(supplierId);
    }

    @Override
    public Optional<SupplierDTO> searchSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        Optional<Supplier> supplierOptional = supplierDAO.findByPk(supplierId);
        if (supplierOptional.isPresent()){
            return Optional.ofNullable(convertor.fromSupplier(supplierOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        return (ArrayList<SupplierDTO>) supplierDAO.getAll().stream().map(supplier -> convertor.fromSupplier(supplier)).collect(Collectors.toList());
    }
}
