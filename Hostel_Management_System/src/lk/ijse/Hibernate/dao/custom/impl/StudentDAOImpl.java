package lk.ijse.Hibernate.dao.custom.impl;

import lk.ijse.Hibernate.dao.custom.StudentDAO;
import lk.ijse.Hibernate.entity.Student;

import java.util.ArrayList;

/**
    @author : Hasii-boy
**/

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean exits(String s) throws Exception {
        return false;
    }

    @Override
    public Student find(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Student> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(Student dto) throws Exception {
        return false;
    }
}
