package se.ifmo.soa.DAO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "route")
public class RoutesDAO {

    private Long id;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "coordinates")
    private CoordinatesDAO coordinates;

    private String creationdate;

    private LocationDAO from;

    private LocationDAO to;

    @JacksonXmlProperty(localName = "distance")
    private Integer distance;
}
