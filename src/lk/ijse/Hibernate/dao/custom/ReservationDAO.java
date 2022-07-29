package lk.ijse.Hibernate.dao.custom;

import lk.ijse.Hibernate.dao.CrudDAO;
import lk.ijse.Hibernate.entity.Reservation;

/**
    @author : Hasii-boy
**/
public interface ReservationDAO extends CrudDAO<Reservation, String> {

    boolean exitsRoomId(String id)throws Exception;
}
