package by.epam.producer.util;

import by.epam.producer.entity.BookingEvent;
import org.junit.Assert;
import org.junit.Test;

import java.awt.print.Book;

import static org.junit.Assert.*;

public class BookingEventSerializerTest {

    @Test
    public void testSerialize() {

        BookingEvent event = generateBookingEvent();

        byte[] expectedBytes = generateSerializedBytes();
        byte[] actualBytes = new BookingEventSerializer().serialize("test", event);

        Assert.assertArrayEquals(expectedBytes, actualBytes);

    }

    @Test
    public void testSerializeWithNullParam() {
        byte[] expectedBytes = null;
        byte[] actualBytes = new BookingEventSerializer().serialize("test", null);

        Assert.assertArrayEquals(expectedBytes, actualBytes);
    }

    private BookingEvent generateBookingEvent() {
        BookingEvent event = new BookingEvent();
        event.setDateTime("2014-08-11 07:46:59");
        event.setSiteName(2);
        event.setPosaContinent(3);
        event.setUserLocationCountry(66);
        event.setUserLocationRegion(348);
        event.setUserLocationCity(48862);
        event.setOrigDestinationDistance(2234.2641);
        event.setUserId(12);
        event.setIsMobile(0);
        event.setIsPackage(1);
        event.setChannel(9);
        event.setSrchCi("2014-08-27");
        event.setSrchCo("2014-08-31");
        event.setSrchAdultsCnt(2);
        event.setSrchChildrenCnt(0);
        event.setSrchRmCnt(1);
        event.setSrchDestinationId(8250);
        event.setSrchDestinationTypeId(1);
        event.setIsBooking(0);
        event.setCnt(3);
        event.setHotelContinent(2);
        event.setHotelCountry(50);
        event.setHotelMarket(628);
        event.setHotelCluster(1);

        return event;
    }

    private byte[] generateSerializedBytes() {
        return "2014-08-11 07:46:59,2,3,66,348,48862,2234.2641,12,0,1,9,2014-08-27,2014-08-31,2,0,1,8250,1,0,3,2,50,628,1".getBytes();
    }
}