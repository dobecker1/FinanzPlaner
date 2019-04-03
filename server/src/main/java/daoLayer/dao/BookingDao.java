package daoLayer.dao;

import models.booking.Booking;
import models.booking.BookingImpl;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("bookingDao")
public class BookingDao extends BasicDao {

    public void write(Booking booking) {
        super.write(booking);
    }

    public Booking read(int id) {
        return super.read(id, BookingImpl.class);
    }

    public void deleteBooking(int id) {
        super.delete(this.read(id));
    }

    public void deleteBooking(Booking booking) {
        super.delete(booking);
    }

    public List<Booking> findAllBookings() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from BookingImpl");
        List<Booking> bookings = query.list();
        session.getTransaction().commit();
        return bookings;
    }
}
