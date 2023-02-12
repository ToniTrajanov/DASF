package mk.ukim.finki.dashw.repository;

import mk.ukim.finki.dashw.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository  extends JpaRepository<Rating, Long> {
}
