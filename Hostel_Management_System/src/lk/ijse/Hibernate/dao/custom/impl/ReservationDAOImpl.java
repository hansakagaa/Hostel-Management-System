package lk.ijse.Hibernate.dao.custom.impl;

import lk.ijse.Hibernate.dao.custom.ReservationDAO;
import lk.ijse.Hibernate.entity.Reservation;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/
public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean exits(String s) throws Exception {
        return false;
    }

    @Override
    public Reservation find(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Reservation> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Reservation dto) throws Exception {
        return false;
    }
}
