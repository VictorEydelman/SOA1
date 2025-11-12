package se.ifmo.soa.DAO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "coordinates")
public class CoordinatesDAO {

    private Long id;

    @JacksonXmlProperty(localName = "x")
    private Integer x;

    @JacksonXmlProperty(localName = "y")
    private Float y;
}
