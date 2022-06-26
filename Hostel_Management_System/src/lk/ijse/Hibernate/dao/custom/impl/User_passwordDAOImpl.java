package lk.ijse.Hibernate.dao.custom.impl;

import lk.ijse.Hibernate.dao.custom.User_passwordDAO;
import lk.ijse.Hibernate.entity.User_password;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/
public class User_passwordDAOImpl implements User_passwordDAO {
    @Override
    public boolean exits(String s) throws Exception {
        return false;
    }

    @Override
    public User_password find(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<User_password> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(User_password dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(User_password dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }
}
