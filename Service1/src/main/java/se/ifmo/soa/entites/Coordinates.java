package se.ifmo.soa.entites;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Entity
@Table(name = "soa_coordinates")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@XmlRootElement
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer x;
    private float y;
}
