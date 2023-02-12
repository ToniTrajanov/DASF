package mk.ukim.finki.dashw.service;

import mk.ukim.finki.dashw.model.Rating;
import mk.ukim.finki.dashw.model.User;

import java.util.List;

public interface RatingService {
    List<Rating> findAll();
    Rating create(User user, Integer number, String comment);
    Rating create(User user, String comment);
    Rating create(User user, Integer number);
    Rating edit(Long id, User user, Integer number, String comment);
    Rating edit(Long id,User user, String comment);
    Rating edit(Long id,User user, Integer number);
    Double calculate();
}
