package ru.itmo.service2.domain;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "routes")
public class RouteList implements Serializable {

    @Getter(onMethod_ = @XmlElement(name = "route"))
    private List<Route> routes = new ArrayList<>();

    public static RouteList of(Route... routes) {
        return new RouteList(List.of(routes));
    }

}
