package by.epam.producer.util;

import by.epam.producer.entity.BookingEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class BookingEventSerializer implements Serializer<BookingEvent> {

    private static final String COMMA = ",";

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    /**
     * Serializes {@link BookingEvent} object as a csv row.
     *
     * @param topic is a name of the topic.
     * @param event is {@link BookingEvent} object to serialize.
     * @return array of bytes.
     */
    @Override
    public byte[] serialize(String topic, BookingEvent event) {

        if (event == null) {
            return null;
        }

        return toCsvRow(event).getBytes();

    }

    @Override
    public void close() {

    }

    /**
     * Converts {@link BookingEvent} object to {@link String} object representing a csv row.
     *
     * @param event is a {@link BookingEvent} object.
     * @return {@link String} object representing a csv row.
     */
    private String toCsvRow(BookingEvent event) {
        return event.getDateTime() + COMMA + event.getSiteName() + COMMA + event.getPosaContinent() + COMMA + event.getUserLocationCountry() + COMMA + event.getUserLocationRegion() + COMMA + event.getUserLocationCity() + COMMA +
                event.getOrigDestinationDistance() + COMMA + event.getUserId() + COMMA + event.getIsMobile() + COMMA + event.getIsPackage() + COMMA + event.getChannel() + COMMA +
                event.getSrchCi() + COMMA + event.getSrchCo() + COMMA + event.getSrchAdultsCnt() + COMMA + event.getSrchChildrenCnt() + COMMA + event.getSrchRmCnt() + COMMA +
                event.getSrchDestinationId() + COMMA + event.getSrchDestinationTypeId() + COMMA + event.getIsBooking() + COMMA + event.getCnt() + COMMA +
                event.getHotelContinent() + COMMA + event.getHotelCountry() + COMMA + event.getHotelMarket() + COMMA + event.getHotelCluster();
    }

}
