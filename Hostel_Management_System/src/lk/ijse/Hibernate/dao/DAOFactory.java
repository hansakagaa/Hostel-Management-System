package lk.ijse.Hibernate.dao;

/**
    @author : Hasii-boy
**/
public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO>T getDAO(DAOType daoType){
        switch (daoType){
//            case CUSTOMER:
//                return (T) new CustomerDAOImpl();
            default:
                return null;
        }
    }
}
