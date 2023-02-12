package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.SystemUsersDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.SystemUsersDTO;
import lk.ijse.phone.entity.SystemUsers;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SystemUsersDAOImpl implements SystemUsersDAO {
    @Override
    public boolean save(SystemUsers systemUsers) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO systemUsers VALUES(?,?,?,?,?)",
                systemUsers.getId(),
                systemUsers.getName(),
                systemUsers.getEmail(),
                systemUsers.getUserRank(),
                systemUsers.getPassword()
        );
    }

    @Override
    public boolean update(SystemUsers systemUsers) throws SQLException, ClassNotFoundException {
        boolean i = CrudUtil.execute("UPDATE systemUsers SET name = ? , email = ? , userRank = ? , password = ? WHERE id = ?",
                systemUsers.getName(),
                systemUsers.getEmail(),
                systemUsers.getUserRank(),
                systemUsers.getPassword(),
                systemUsers.getId()
        );
        return  i;
    }

    @Override
    public boolean deleteByPk(String id) throws SQLException, ClassNotFoundException {
        return  DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From systemUsers where id='"+id+"'")>0;
    }

    @Override
    public Optional<SystemUsers> findByPk(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM  systemUsers WHERE id = ? ", id);
        while (rst.next()) {
            SystemUsers systemUsers = new SystemUsers(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
            return Optional.of(systemUsers);
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<SystemUsers> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM systemUsers");
        ArrayList<SystemUsers> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(new SystemUsers(rst.getString(1),rst.getString(2),rst.getString(3), rst.getString(4),rst.getString(5)));
        }
        return arrayList;
    }

}
