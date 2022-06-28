package lk.ijse.Hibernate.util;

import lk.ijse.Hibernate.entity.Reservation;
import lk.ijse.Hibernate.entity.Room;
import lk.ijse.Hibernate.entity.Student;
import lk.ijse.Hibernate.entity.User_password;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

/**
    @author : Hasii-boy
**/

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        // configure() -> load and config Hibernate.cfg.xml file to SessionFactory
        // addAnnotatedClass() -> define which Entity that gonna use to Persist
//        Configuration configuration = new Configuration().configure("/lk/ijse/Hibernate/resources/hibernate.cfg.xml")
//                .addAnnotatedClass(Student.class)
//                .addAnnotatedClass(Room.class)
//                .addAnnotatedClass(Reservation.class)
//                .addAnnotatedClass(User_password.class);

        // build a SessionFactory and assign it to sessionFactory reference
//        sessionFactory = configuration.buildSessionFactory();
        File file = new File("lk/ijse/Hibernate/resources/hibernate.properties");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().loadProperties(file).build();
        sessionFactory = new MetadataSources(registry)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(User_password.class)
                .buildMetadata().buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }
    // return a new Session through sessionFactory
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
