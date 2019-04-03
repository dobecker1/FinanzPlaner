package daoLayer.dao;

import models.basic.BasicModel;
import models.category.Category;
import models.patternBooking.impl.BookingPatternItemImpl;
import models.patternBooking.interfaces.BookingPattern;
import models.patternBooking.impl.BookingPatternImpl;
import models.patternBooking.interfaces.BookingPatternItem;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component("bookingPatternDao")
public class BookingPatternDao extends BasicDao {

    public void write(BookingPattern bookingPattern) {
        super.write(bookingPattern);
    }

    public BookingPattern read(int id) {
        return super.read(id, BookingPatternImpl.class);
    }

    public void deleteBookingPattern(int id) {
        super.delete(this.read(id));
    }

    public void deleteBookingPattern(BookingPattern pattern) {
        super.delete(pattern);
    }

    public List<BookingPattern> findBookingPatternByCategory(Category category) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from BookingPatternImpl where category = " + category);
        List<BookingPattern> patterns = query.list();
        session.getTransaction().commit();
        return patterns;
    }

    public void writeBookingPatternItem(BookingPatternItem patternItem) {
        super.write(patternItem);
    }

    public BookingPatternItem readBookingPatternItem(int id) {
        return super.read(id, BookingPatternItemImpl.class);
    }

    public void deletePatternItem(BookingPatternItem bookingPatternItem) {
        super.delete(bookingPatternItem);
    }

    public List<BookingPatternItem> getAllPatternItems() {
        return this.executeListQuery("from BookingPatternItemImpl");
    }

    public List<BookingPatternItem> getPatternItemsByStartEndDate(Date startDate, Date endDate) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from BookingPatternItemImpl where booking.date >= :startDate and booking.date <= :endDate");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<BookingPatternItem> items = query.list();
        session.getTransaction().commit();
        return items;
    }

    private <T extends BasicModel> List<T> executeListQuery(String queryString) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(queryString);
        List<T> items = query.list();
        session.getTransaction().commit();
        return items;
    }
}
