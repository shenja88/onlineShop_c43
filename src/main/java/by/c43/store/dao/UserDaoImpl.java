package by.c43.store.dao;

import by.c43.store.entity.Telephone;
import by.c43.store.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    private static final String GET_BY_ID = "from User where id = :id";
    private static final String GET_BY_EMAIL = "from User where email = :email";

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void delete(long id) {
        User user = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void updateName(String newName, long id) {
        User user = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        user.setName(newName);
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void updateEmail(String newEmail, long id) {
        User user = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        user.setEmail(newEmail);
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void updatePassword(String newPassword, long id) {
        User user = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        user.setPassword(newPassword);
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void updatePicture(String newPicture, long id) {
        User user = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
        user.setPicture(newPicture);
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void updateTelephone(long userId, Telephone telephone) {
        User user = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", userId)
                .getSingleResult();
        user.setTelephone(telephone);
        sessionFactory.getCurrentSession().update(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(long id) {
        return sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public User getByEmail(String email) {
        return sessionFactory.getCurrentSession()
                .createQuery(GET_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public boolean isExistById(long id) {
        Optional<User> optionalUser = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, User.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        return optionalUser.isPresent();
    }

    @Override
    public boolean isExistByEmail(String email) {
        Optional<User> optionalUser = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_EMAIL, User.class)
                .setParameter("email", email)
                .uniqueResultOptional();
        return optionalUser.isPresent();
    }
}
