package by.c43.store.service;

import by.c43.store.dao.RatingDao;
import by.c43.store.entity.Rating;
import by.c43.store.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingService {

    private final RatingDao ratingDao;

    public boolean saveRating(Rating rating){
        if(ratingDao.isExistById(rating.getId())){
            ratingDao.saveRating(rating);
            return true;
        }else return false;
    }

    public boolean updateScore(double newScore, long id, User user) {
        Rating rating = ratingDao.getById(id);
        if(!rating.getUsers().contains(user.getId())){
            ratingDao.setNewScore(newScore, id);
            return true;
        }else return false;
    }
}
