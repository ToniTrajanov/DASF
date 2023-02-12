package mk.ukim.finki.dashw.service.impl;

import mk.ukim.finki.dashw.model.Rating;
import mk.ukim.finki.dashw.model.User;
import mk.ukim.finki.dashw.model.exceptions.RatingNotFoundException;
import mk.ukim.finki.dashw.repository.RatingRepository;
import mk.ukim.finki.dashw.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating create(User user, Integer number, String comment) {
        Rating rating = new Rating(user, number, comment);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating create(User user, String comment) {
        Rating rating = new Rating(user, comment);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating create(User user, Integer number) {
        Rating rating = new Rating(user, number);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating edit(Long id, User user, Integer number, String comment) {

        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException());
        rating.setUser(user);
        rating.setNumber(number);
        rating.setComment(comment);
        return ratingRepository.save(rating);

    }

    @Override
    public Rating edit(Long id, User user, String comment) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException());
        rating.setUser(user);

        rating.setComment(comment);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating edit(Long id, User user, Integer number) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException());
        rating.setUser(user);
        rating.setNumber(number);
        return ratingRepository.save(rating);
    }

    @Override
    public Double calculate() {
        double sum = ratingRepository.findAll().stream().mapToDouble(i -> i.getNumber()).sum();
        return sum / ratingRepository.findAll().size();
    }


}

