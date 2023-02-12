package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.SystemUsersDAO;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.entity.SystemUsers;
import lk.ijse.phone.service.custom.SystemUserService;
import lk.ijse.phone.dto.SystemUsersDTO;
import lk.ijse.phone.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class SystemUserServiceImpl implements SystemUserService {
    private final SystemUsersDAO systemUsersDAO = (SystemUsersDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.SYSTEM_USERS);
    private final Convertor convertor = new Convertor();

    @Override
    public boolean saveSystemUsers(SystemUsersDTO systemUsersDTO) throws SQLException, ClassNotFoundException {
        return systemUsersDAO.save(convertor.toSystemUsers(systemUsersDTO));
    }

    @Override
    public boolean updateSystemUsers(SystemUsersDTO systemUsersDTO) throws SQLException, ClassNotFoundException {
        return systemUsersDAO.update(convertor.toSystemUsers(systemUsersDTO));
    }

    @Override
    public boolean deleteSystemUsers(String id) throws SQLException, ClassNotFoundException {
        return systemUsersDAO.deleteByPk(id);
    }

    @Override
    public Optional<SystemUsersDTO> searchSystemUsers(String id) throws SQLException, ClassNotFoundException {
        Optional<SystemUsers> optionalSystemUsers = systemUsersDAO.findByPk(id);
        if (optionalSystemUsers.isPresent()){
            return Optional.ofNullable(convertor.fromSystemUser(optionalSystemUsers.get()));
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<SystemUsersDTO> getAllSystemUsers() throws SQLException, ClassNotFoundException {
        return (ArrayList<SystemUsersDTO>) systemUsersDAO.getAll().stream().map(systemUsers -> convertor.fromSystemUser(systemUsers)).collect(Collectors.toList());
    }
}
