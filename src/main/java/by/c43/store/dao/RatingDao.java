package by.c43.store.dao;

import by.c43.store.entity.Rating;

public interface RatingDao {

    Rating getById(long id);

    void setNewScore(double newScore, long ratingId, long userId);

    boolean isExistById(long id);
}
