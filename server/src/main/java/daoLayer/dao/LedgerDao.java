package daoLayer.dao;

import models.ledger.Ledger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LedgerDao extends BasicDao{

    public LedgerDao() {
        super();
    }


    public void write(Ledger ledger) {
        super.write(ledger);
    }

    public Ledger findLedgerByNumber(int ledgerNumber) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Ledger where ledgerNumber = :ledgerNumber");
        query.setParameter("ledgerNumber", ledgerNumber);
        Ledger ledger = (Ledger) query.getSingleResult();
        return ledger;
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
