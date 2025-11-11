package se.ifmo.soa.DAO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "location")
public class LocationDAO {

    private Long id;

    @JacksonXmlProperty(localName = "x")
    private Long x;

    @JacksonXmlProperty(localName = "y")
    private Long y;

    @JacksonXmlProperty(localName = "z")
    private Long z;

    @JacksonXmlProperty(localName = "name")
    private String name;
}
