package com.kozitski.producer.util;

import com.kozitski.producer.domain.BookingEvent;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * The type Booking event serializer.
 */
@Component
public class BookingEventSerializer implements Serializer<BookingEvent> {

    private static final String COMMA = ",";

    @Override
    public byte[] serialize(String topic, BookingEvent event) {
        return event == null ? new byte[NumberUtils.BYTE_ZERO] : toCsvRow(event).getBytes();
    }

    private String toCsvRow(BookingEvent event) {
        return  event.getDateTime() + COMMA + event.getSiteName() + COMMA + event.getPosaContinent() + COMMA +
                event.getUserLocationCountry() + COMMA + event.getUserLocationRegion() + COMMA + event.getUserLocationCity() + COMMA +
                event.getOrigDestinationDistance() + COMMA + event.getUserId() + COMMA + event.getIsMobile() + COMMA + event.getIsPackage() + COMMA +
                event.getChannel() + COMMA + event.getSrchCi() + COMMA + event.getSrchCo() + COMMA + event.getSrchAdultsCnt() + COMMA +
                event.getSrchChildrenCnt() + COMMA + event.getSrchRmCnt() + COMMA + event.getSrchDestinationId() + COMMA + event.getSrchDestinationTypeId() + COMMA +
                event.getIsBooking() + COMMA + event.getCnt() + COMMA + event.getHotelContinent() + COMMA + event.getHotelCountry() + COMMA +
                event.getHotelMarket() + COMMA + event.getHotelCluster();
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {/* NOP */ }

    @Override
    public void close() { /* NOP */ }

}
