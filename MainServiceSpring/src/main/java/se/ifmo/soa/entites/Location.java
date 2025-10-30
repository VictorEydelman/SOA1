package se.ifmo.soa.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Entity
@Table(name = "soa_location")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@XmlRootElement
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long x;
    @Column(nullable = false)
    private Long y; //Поле не может быть null
    private long z;
    @Column(length = 588)
    @Size(max = 588)
    private String name; //Длина строки не должна быть больше 5
}
