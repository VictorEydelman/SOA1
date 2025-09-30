package se.ifmo.soa.DAO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoutesDAO {

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "coordinates")
    private CoordinatesDAO coordinates;

    private LocationDAO from;

    private LocationDAO to;

    @JacksonXmlProperty(localName = "distance")
    private Integer distance;
}
