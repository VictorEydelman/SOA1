package se.ifmo.soa.DAO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.ifmo.soa.entites.Coordinates;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoutesDAO {

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "coordinates")
    private CoordinatesDAO coordinates;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "location")
    private List<LocationDAO> locations;

    @JacksonXmlProperty(localName = "distance")
    private Integer distance;
}
