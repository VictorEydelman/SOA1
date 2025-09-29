package se.ifmo.soa.DAO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoordinatesDAO {
    @JacksonXmlProperty(localName = "x")
    private Integer x;

    @JacksonXmlProperty(localName = "y")
    private Float y;
}
