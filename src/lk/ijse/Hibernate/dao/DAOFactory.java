package lk.ijse.Hibernate.dao;

import lk.ijse.Hibernate.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.Hibernate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.Hibernate.dao.custom.impl.StudentDAOImpl;
import lk.ijse.Hibernate.dao.custom.impl.User_passwordDAOImpl;

/**
    @author : Hasii-boy
**/
public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <DAO extends SuperDAO>DAO getDAO(DAOType daoType){
        switch (daoType){
            case STUDENT:
                return (DAO) new StudentDAOImpl();
            case ROOM:
                return (DAO) new RoomDAOImpl();
            case RESERVATION:
                return (DAO) new ReservationDAOImpl();
            case USER:
                return (DAO) new User_passwordDAOImpl();
            default:
                return null;
        }
    }
}
