package lk.ijse.Hibernate.dao.custom.impl;

import lk.ijse.Hibernate.dao.custom.ReservationDAO;
import lk.ijse.Hibernate.entity.Reservation;
import lk.ijse.Hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
    @author : Hasii-boy
**/
public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean exits(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public Reservation find(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation = session.find(Reservation.class, id);

        transaction.commit();
        session.close();
        return reservation;
    }

    @Override
    public ArrayList<Reservation> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Reservation> reservation = session.createQuery("FROM Reservation ").list();

        transaction.commit();
        session.close();
        return (ArrayList<Reservation>) reservation;
    }

    @Override
    public boolean save(Reservation entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(Reservation entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation load = session.load(Reservation.class, id);
        session.delete(load);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exitsRoomId(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        transaction.commit();
        session.close();
        return false;
    }
}
