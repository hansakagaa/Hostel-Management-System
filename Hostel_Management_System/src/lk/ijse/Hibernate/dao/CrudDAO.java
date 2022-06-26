package lk.ijse.Hibernate.dao;

import java.util.ArrayList;

/**
 * @author : Hasii-boy
 **/
public interface CrudDAO<DAO,ID> extends SuperDAO{

    boolean exits(ID id) throws Exception;

    DAO find(ID id) throws Exception;

    ArrayList<DAO> getAll() throws Exception;

    boolean save(DAO dto) throws Exception;

    boolean update(DAO dto) throws Exception;

    boolean delete(ID id) throws Exception;

}
