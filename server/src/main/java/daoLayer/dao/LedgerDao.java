package daoLayer.dao;

import models.ledger.Ledger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LedgerDao extends BasicDao{

    public LedgerDao() {
        super();
    }

    public void saveLedger(Ledger ledger) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(ledger);
        session.getTransaction().commit();
    }

    public List<Ledger> findAllLedgers() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Ledger");
        List<Ledger> ledgers = query.list();
        session.getTransaction().commit();
        return ledgers;
    }
}
