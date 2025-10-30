package se.ifmo.soa.DAO;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import se.ifmo.soa.entites.Route;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "routes")
public class RouteList {

    @XmlElement(name = "route")
    private List<Route> routes;

    public static RouteList of(Route... routes) {
        return new RouteList(List.of(routes));
    }
}