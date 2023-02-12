package mk.ukim.finki.dashw.repository;

import mk.ukim.finki.dashw.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findAllByMunicipality(String municipality);
    Hospital findByNameIsLike(String name);
    @Query("select h from Hospital h order by h.searchCount desc")
    List<Hospital> findBySearchCount();
}
