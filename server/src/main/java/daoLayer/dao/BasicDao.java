package daoLayer.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class BasicDao {

    protected SessionFactory sessionFactory;

    public BasicDao() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }
}
