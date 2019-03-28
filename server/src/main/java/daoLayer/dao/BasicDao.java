package daoLayer.dao;

import models.basic.BasicModel;
import models.ledger.Ledger;
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

    public <T extends BasicModel> T read(int id, Class<T> tClass) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        T model = session.get(tClass, id);
        session.getTransaction().commit();
        return model;
    }

    protected void delete(BasicModel model) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(model);
        session.getTransaction().commit();
    }
}
