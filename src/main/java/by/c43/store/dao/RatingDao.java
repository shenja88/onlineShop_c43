package by.c43.store.dao;

import by.c43.store.entity.Rating;

public interface RatingDao {

    void saveRating(Rating rating);

    Rating getById(long id);

    void setNewScore(double newScore, long id);

    boolean isExistById(long id);
}
