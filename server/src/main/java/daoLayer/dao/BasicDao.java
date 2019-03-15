package daoLayer.dao;

import models.basic.BasicModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class BasicDao {

    protected SessionFactory sessionFactory;

    public BasicDao() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void write(BasicModel model) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
    }
}
