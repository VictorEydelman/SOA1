package se.ifmo.soa;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ZonedDateTimeXmlAdapter extends XmlAdapter<String, ZonedDateTime> {
    @Override
    public ZonedDateTime unmarshal(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return ZonedDateTime.parse(value).withZoneSameLocal(ZoneOffset.systemDefault());
    }

    @Override
    public String marshal(ZonedDateTime value) {
        if (value == null) {
            return null;
        }
        return value.format(DateTimeFormatter.ISO_INSTANT);
    }
}