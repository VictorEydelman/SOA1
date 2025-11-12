package se.ifmo.soa.entites;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import se.ifmo.soa.ZonedDateTimeXmlAdapter;

@Entity
@Table(name = "soa_route")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "coordinates_id", nullable = false)
    private Coordinates coordinates; //Поле не может быть null
    @Column(nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    @XmlJavaTypeAdapter(ZonedDateTimeXmlAdapter.class)
    private java.time.ZonedDateTime creationdate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "from_location_id")
    private Location from; //Поле может быть null
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_location_id", nullable = false)
    private Location to; //Поле не может быть null
    private Integer distance; //Поле может быть null, Значение поля должно быть больше 1
}
