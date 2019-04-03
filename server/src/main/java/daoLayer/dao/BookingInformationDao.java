package daoLayer.dao;

import models.patternBooking.interfaces.BookingInformation;
import models.patternBooking.impl.BookingInformationImpl;
import org.springframework.stereotype.Component;

@Component("bookingInformationDao")
public class BookingInformationDao extends BasicDao {

    public void write(BookingInformation bookingInformation) {
        super.write(bookingInformation);
    }

    public BookingInformation read(int id) {
        return super.read(id, BookingInformationImpl.class);
    }

    public void deleteBookingInformation(int id) {
        super.delete(this.read(id));
    }

    public void deleteBookingInformation(BookingInformation bookingInformation) {
        super.delete(bookingInformation);
    }

}
