package se.ifmo.soa.DAO;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import se.ifmo.soa.entites.Route;

import java.util.List;

@Getter
@AllArgsConstructor
@XmlRootElement
public class PaginatedResult {
    private List<Route> data;
    private int currentPage;
    private int pageSize;
    private long totalCount;
    private int totalPages;
}
