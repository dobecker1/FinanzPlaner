package daoLayer.dao;

import models.booking.Booking;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("bookingDao")
public class BookingDao extends BasicDao {

    public void write(Booking booking) {
        super.write(booking);
    }

    public List<Booking> findAllBookings() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Booking");
        List<Booking> bookings = query.list();
        session.getTransaction().commit();
        return bookings;
    }
}
