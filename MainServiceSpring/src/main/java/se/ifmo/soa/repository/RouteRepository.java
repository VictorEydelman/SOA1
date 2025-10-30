package se.ifmo.soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.ifmo.soa.entites.Route;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {

     @Query("SELECT SUM(r.distance) FROM Route r")
    Integer sumDistance();

    @Query("SELECT COUNT(r.distance) FROM Route r WHERE r.distance < :threshold")
    Long countDistanceLessThan(@Param("threshold") Integer threshold);

    List<Route> findAllByNameLike(String prefix);

    //Page<Route> findAll(Specification<Route> spec, Pageable pageable);
}
