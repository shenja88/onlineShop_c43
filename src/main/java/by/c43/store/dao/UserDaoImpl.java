package by.c43.store.dao;

import by.c43.store.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Component
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static final String GET_BY_ID = "from User where id =: id";
    private static final String GET_BY_EMAIL = "from User where email =: email";

    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session
                    .createQuery(GET_BY_ID, User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.delete(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateName(String newName, long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session
                    .createQuery(GET_BY_ID, User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            user.setName(newName);
            session.update(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmail(String newEmail, long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session
                    .createQuery(GET_BY_ID, User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            user.setEmail(newEmail);
            session.update(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(String newPassword, long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session
                    .createQuery(GET_BY_ID, User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            user.setPassword(newPassword);
            session.update(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePicture(String newPicture, long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session
                    .createQuery(GET_BY_ID, User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            user.setPicture(newPicture);
            session.update(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(long id) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            user = session
                    .createQuery(GET_BY_ID, User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            user = session
                    .createQuery(GET_BY_EMAIL, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean isExistById(long id) {
        Optional<User> optionalUser = Optional.empty();
        try (Session session = sessionFactory.openSession()) {
            optionalUser = session
                    .createQuery(GET_BY_ID, User.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return optionalUser.isPresent();
    }

    @Override
    public boolean isExistByEmail(String email) {
        Optional<User> optionalUser = Optional.empty();
        try (Session session = sessionFactory.openSession()) {
            optionalUser = session
                    .createQuery(GET_BY_EMAIL, User.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return optionalUser.isPresent();
    }
}
