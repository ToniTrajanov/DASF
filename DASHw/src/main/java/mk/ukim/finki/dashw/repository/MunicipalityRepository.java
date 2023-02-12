package mk.ukim.finki.dashw.repository;

import mk.ukim.finki.dashw.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    Municipality findByName(String name);
}
