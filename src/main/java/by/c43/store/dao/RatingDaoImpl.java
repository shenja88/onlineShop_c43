package by.c43.store.dao;

import by.c43.store.entity.Rating;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class RatingDaoImpl implements RatingDao{

    private final SessionFactory sessionFactory;

    private final static String GET_BY_ID = "from Rating where id =: id";

    public RatingDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveRating(Rating rating) {
        Session session = sessionFactory.openSession();
        session.save(rating);
        session.close();
    }

    @Transactional(readOnly = true)
    @Override
    public Rating getById(long id) {
        Session session = sessionFactory.openSession();
        Rating rating = session.get(Rating.class, id);
        session.close();
        return rating;
    }

    @Override
    public void setNewScore(double newScore, long id) {
        Session session = sessionFactory.openSession();
        Rating rating = session.get(Rating.class, id);
        rating.setScore(newScore);
        session.update(rating);
        session.close();
    }

    @Override
    public boolean isExistById(long id) {
        Session session = sessionFactory.openSession();
        Optional<Rating> optionalRating = session.createQuery(GET_BY_ID, Rating.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        session.close();
        return optionalRating.isPresent();
    }
}
