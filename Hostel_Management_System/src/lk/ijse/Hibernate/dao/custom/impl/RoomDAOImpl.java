package lk.ijse.Hibernate.dao.custom.impl;

import lk.ijse.Hibernate.dao.custom.RoomDAO;
import lk.ijse.Hibernate.entity.Room;
import lk.ijse.Hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
    @author : Hasii-boy
**/
public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean exits(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT id FROM Room WHERE id=:id");
        String rId = String.valueOf(query.setParameter("id", id).uniqueResult());
        if (rId != null) {
            return true;
        }

        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public Room find(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.find(Room.class, id);

        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public ArrayList<Room> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Room> room = session.createQuery("FROM Room ").list();

        transaction.commit();
        session.close();
        return (ArrayList<Room>) room;
    }

    @Override
    public boolean save(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
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

        Room room = session.load(Room.class, id);
        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exitsType(String type) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT type FROM Room WHERE type=:type");
        String roomType = String.valueOf(query.setParameter("type", type).uniqueResult());
        if (roomType != null) {
            return true;
        }

        transaction.commit();
        session.close();
        return false;
    }
}
