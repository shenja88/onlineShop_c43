package by.c43.store.dao;

import by.c43.store.entity.Telephone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Component
public class TelephonesDaoImpl implements TelephoneDao {
    private final SessionFactory sessionFactory;

    public TelephonesDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static final String GET_BY_ID = "from Telephone where id =: id";
    private static final String GET_BY_NUMBER = "from Telephone where number =: number";

    @Override
    public void delete(long id) {
        Telephone telephone = sessionFactory.getCurrentSession().get(Telephone.class, id);
        sessionFactory.getCurrentSession().delete(telephone);
    }

    @Transactional(readOnly = true)
    @Override
    public Telephone getById(long id) {
        return sessionFactory.getCurrentSession().get(Telephone.class, id);
    }

    @Override
    public boolean isExistById(long id) {
        Optional<Telephone> telephone = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, Telephone.class)
                .setParameter("id", id)
                .uniqueResultOptional();;
        return telephone.isPresent();
    }

    @Override
    public boolean isExistByNumber(String number) {
        Optional<Telephone> telephone = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_NUMBER, Telephone.class)
                .setParameter("number", number)
                .uniqueResultOptional();
        return telephone.isPresent();
    }
}
