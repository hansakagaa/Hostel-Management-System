package lk.ijse.Hibernate.dao.custom;

import lk.ijse.Hibernate.dao.CrudDAO;
import lk.ijse.Hibernate.entity.Room;

/**
    @author : Hasii-boy
**/
public interface RoomDAO extends CrudDAO<Room, String> {

    boolean exitsType(String type)throws Exception;
}
