package lk.ijse.Hibernate.dao.custom.impl;

import lk.ijse.Hibernate.dao.custom.RoomDAO;
import lk.ijse.Hibernate.entity.Room;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/
public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean exits(String s) throws Exception {
        return false;
    }

    @Override
    public Room find(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Room> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Room dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(Room dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean exitsType(String type) throws Exception {
        return false;
    }
}
