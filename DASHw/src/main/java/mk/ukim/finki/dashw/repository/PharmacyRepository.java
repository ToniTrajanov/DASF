package mk.ukim.finki.dashw.repository;


import mk.ukim.finki.dashw.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    List<Pharmacy> findAllByMunicipalityIsLike(String municipality);
    Pharmacy findByNameIsLike(String name);
    @Query("select p from Pharmacy p order by p.searchCount desc")
    List<Pharmacy> findBySearchCount();
}
