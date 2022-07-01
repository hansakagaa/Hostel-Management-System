package lk.ijse.Hibernate.dao.custom.impl;

import lk.ijse.Hibernate.dao.custom.User_passwordDAO;
import lk.ijse.Hibernate.entity.User_password;
import lk.ijse.Hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
    @author : Hasii-boy
**/
public class User_passwordDAOImpl implements User_passwordDAO {
    @Override
    public boolean exits(String user) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT userName FROM User_password WHERE userName=:id");
        String uId = String.valueOf(query.setParameter("id", user).uniqueResult());
        if (uId != null) {
            return true;
        }

        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public User_password find(String user) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User_password userPassword = session.find(User_password.class, user);

        transaction.commit();
        session.close();
        return userPassword;
    }

    @Override
    public ArrayList<User_password> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<User_password> userPassword = session.createQuery("from User_password ").list();

        transaction.commit();
        session.close();
        return (ArrayList<User_password>) userPassword;
    }

    @Override
    public boolean save(User_password entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User_password entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String user) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User_password userPassword = session.load(User_password.class, user);
        session.delete(userPassword);

        transaction.commit();
        session.close();
        return true;
    }
}
