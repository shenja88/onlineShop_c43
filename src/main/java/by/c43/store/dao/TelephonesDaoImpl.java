package by.c43.store.dao;

import by.c43.store.entity.Telephone;
import org.hibernate.HibernateException;
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
    public void save(Telephone telephone) {
        try (Session session = sessionFactory.openSession()) {
            session.save(telephone);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            Telephone telephone = session
                    .createQuery(GET_BY_ID, Telephone.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.delete(telephone);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Telephone getById(long id) {
        Telephone telephone = null;
        try (Session session = sessionFactory.openSession()) {
            telephone = session
                    .createQuery(GET_BY_ID, Telephone.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return telephone;
    }

    @Override
    public boolean isExistById(long id) {
        Optional<Telephone> telephone = Optional.empty();
        try (Session session = sessionFactory.openSession()) {
            telephone = session
                    .createQuery(GET_BY_ID, Telephone.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return telephone.isPresent();
    }

    @Override
    public boolean isExistByNumber(String number) {
        Optional<Telephone> telephone = Optional.empty();
        try (Session session = sessionFactory.openSession()) {
            telephone = session
                    .createQuery(GET_BY_NUMBER, Telephone.class)
                    .setParameter("number", number)
                    .uniqueResultOptional();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return telephone.isPresent();
    }
}
