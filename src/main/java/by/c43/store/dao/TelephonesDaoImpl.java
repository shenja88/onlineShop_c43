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
        Session session = sessionFactory.openSession();
        Telephone telephone = session.get(Telephone.class, id);
        session.delete(telephone);
        session.close();
    }

    @Transactional(readOnly = true)
    @Override
    public Telephone getById(long id) {
        Session session = sessionFactory.openSession();
        Telephone telephone = session.get(Telephone.class, id);
        session.close();
        return telephone;
    }

    @Override
    public boolean isExistById(long id) {
        Session session = sessionFactory.openSession();
        Optional<Telephone> telephone = session
                .createQuery(GET_BY_ID, Telephone.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        session.close();
        return telephone.isPresent();
    }

    @Override
    public boolean isExistByNumber(String number) {
        Session session = sessionFactory.openSession();
        Optional<Telephone> telephone = session
                .createQuery(GET_BY_NUMBER, Telephone.class)
                .setParameter("number", number)
                .uniqueResultOptional();
        session.close();
        return telephone.isPresent();
    }
}
