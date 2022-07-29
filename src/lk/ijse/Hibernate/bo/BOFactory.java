package lk.ijse.Hibernate.bo;

import lk.ijse.Hibernate.bo.custom.impl.*;

/**
    @author : Hasii-boy
**/
public class BOFactory { private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return (null == boFactory) ? boFactory = new BOFactory() : boFactory;
    }

    public <BO extends SuperBO> BO getBO(BOType boType){
        switch (boType){
            case STUDENT:
                return (BO) new StudentBOImpl();
            case ROOM:
                return (BO) new RoomBOImpl();
            case RESERVATION:
                return (BO) new ReservationBoImpl();
            case DASHBOARD:
                return (BO) new DashboardBOImpl();
            case USER:
                return (BO) new User_passwordBOImpl();
            default:
                return null;
        }
    }

}
