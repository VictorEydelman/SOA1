package se.ifmo.soa.DAO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
