package lk.ijse.Hibernate.bo.custom.impl;

import lk.ijse.Hibernate.bo.custom.RoomBO;
import lk.ijse.Hibernate.dao.DAOFactory;
import lk.ijse.Hibernate.dao.DAOType;
import lk.ijse.Hibernate.dao.custom.RoomDAO;
import lk.ijse.Hibernate.dto.RoomDTO;
import lk.ijse.Hibernate.entity.Room;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/
public class RoomBOImpl implements RoomBO {
    private final RoomDAO roomDAO = DAOFactory.getInstance().getDAO(DAOType.ROOM);
    @Override
    public boolean exitsRoom(String id) throws Exception {
        return roomDAO.exits(id);
    }

    @Override
    public boolean exitsRoomType(String type) throws Exception {
        return roomDAO.exitsType(type);
    }

    @Override
    public boolean saveRoom(RoomDTO dto) throws Exception {
        return roomDAO.save(new Room(dto.getRId(),dto.getType(),dto.getKeyMoney(),dto.getRoomQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws Exception {
        return roomDAO.update(new Room(dto.getRId(),dto.getType(),dto.getKeyMoney(),dto.getRoomQty()));
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        return roomDAO.delete(id);
    }

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws Exception {
        ArrayList<Room> allRoom = roomDAO.getAll();
        ArrayList<RoomDTO> dtoS = new ArrayList<>();
        for (Room room : allRoom) {
            dtoS.add(new RoomDTO(room.getRId(),room.getType(),room.getKeyMoney(),room.getRoomQty()));
        }
        return dtoS;
    }
}
