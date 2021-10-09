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
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        User user = session
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        session.delete(user);
        session.close();
    }

    @Override
    public void updateName(String newName, long id) {
        Session session = sessionFactory.openSession();
        User user = session
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        user.setName(newName);
        session.update(user);
        session.close();
    }

    @Override
    public void updateEmail(String newEmail, long id) {
        Session session = sessionFactory.openSession();
        User user = session
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        user.setEmail(newEmail);
        session.update(user);
        session.close();
    }

    @Override
    public void updatePassword(String newPassword, long id) {
        Session session = sessionFactory.openSession();
        User user = session
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        user.setPassword(newPassword);
        session.update(user);
        session.close();
    }

    @Override
    public void updatePicture(String newPicture, long id) {
        Session session = sessionFactory.openSession();
        User user = session
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        user.setPicture(newPicture);
        session.update(user);
        session.close();
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(long id) {
        Session session = sessionFactory.openSession();
        User user = session
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        session.close();
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User getByEmail(String email) {
        Session session = sessionFactory.openSession();
        User user = session
                .createQuery(GET_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getSingleResult();
        session.close();
        return user;
    }

    @Override
    public boolean isExistById(long id) {
        Session session = sessionFactory.openSession();
        Optional<User> optionalUser = session
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        session.close();
        return optionalUser.isPresent();
    }

    @Override
    public boolean isExistByEmail(String email) {
        Session session = sessionFactory.openSession();
        Optional<User> optionalUser = session
                .createQuery(GET_BY_EMAIL, User.class)
                .setParameter("email", email)
                .uniqueResultOptional();
        session.close();
        return optionalUser.isPresent();
    }
}
