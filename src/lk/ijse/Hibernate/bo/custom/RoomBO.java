package lk.ijse.Hibernate.bo.custom;

import lk.ijse.Hibernate.bo.SuperBO;
import lk.ijse.Hibernate.dto.RoomDTO;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/
public interface RoomBO extends SuperBO {

    boolean exitsRoom(String id) throws Exception;

    boolean exitsRoomType(String type) throws Exception;

    boolean saveRoom(RoomDTO dto)throws Exception;

    boolean updateRoom(RoomDTO dto)throws Exception;

    boolean deleteRoom(String id)throws Exception;

    ArrayList<RoomDTO> getAllRoom() throws Exception;
}
