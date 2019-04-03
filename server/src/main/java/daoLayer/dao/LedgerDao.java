package daoLayer.dao;

import models.basic.BasicModel;
import models.ledger.Ledger;
import models.ledger.LedgerImpl;
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

    public void deleteLedger(int id) {
        Ledger ledger = super.<LedgerImpl>read(id, LedgerImpl.class);
        super.delete(ledger);
    }

    public void deleteLedger(Ledger ledger) {
        super.delete(ledger);
    }

    public Ledger findLedgerByNumber(int ledgerNumber) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from LedgerImpl where ledgerNumber = :ledgerNumber");
        query.setParameter("ledgerNumber", ledgerNumber);
        Ledger ledger = (Ledger) query.getSingleResult();
        session.getTransaction().commit();
        return ledger;
    }

    public List<Ledger> findAllLedgers() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from LedgerImpl");
        List<Ledger> ledgers = query.list();
        session.getTransaction().commit();
        return ledgers;
    }


    public void changeLedgerValue(int id, double sum) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Ledger ledger = session.get(Ledger.class, id);
        ledger.setValue(ledger.getValue() + sum);
        session.saveOrUpdate(ledger);
        session.getTransaction().commit();
    }
}
