package se.ifmo.soa.DAO;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Builder;
import se.ifmo.soa.entites.Coordinates;
import se.ifmo.soa.entites.Location;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
public class RouteResponse {
    @XmlElement
    private long id;
    @XmlElement
    private String name;
    //@XmlElement
    //private Coordinates coordinates;
    /*@Column(nullable = false)
    @XmlJavaTypeAdapter(ZonedDateTimeXmlAdapter.class)
    private java.time.ZonedDateTime creationdate;*/
    /*@XmlElement
    private Location from;
    @XmlElement
    private Location to;*/
    @XmlElement
    private Integer distance;
}
