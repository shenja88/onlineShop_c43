package by.c43.store.dao;

import by.c43.store.entity.Address;
import by.c43.store.entity.Producer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
@Component
public class ProducerDaoImpl implements  ProducerDao{


    private  static final String GET_BY_ID = "from Producer where id =: id";
    private  static final String GET_BY_EMAIL = "from Producer where email =: email";


    private final SessionFactory sessionFactory;

    public ProducerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProducer(Producer producer) {
        Session session = sessionFactory.openSession();
        session.save(producer);
        session.close();
    }

    @Override
    public void deleteProducer(long id) {
        Session session = sessionFactory.openSession();
        Producer producer = session.get(Producer.class, id);
        session.delete(producer);
        session.close();
    }

    @Override
    public void updateName(String newName, long id) {
        Session session = sessionFactory.openSession();
        Producer producer = session.get(Producer.class, id);
        producer.setName(newName);
        session.update(producer);
        session.close();
    }

    @Override
    public void updateEmail(String newEmail, long id) {
        Session session = sessionFactory.openSession();
        Producer producer = session.get(Producer.class, id);
        producer.setEmail(newEmail);
        session.update(producer);
        session.close();
    }

    @Override
    public void updatePassword(String newPassword, long id) {
        Session session = sessionFactory.openSession();
        Producer producer = session.get(Producer.class, id);
        producer.setPassword(newPassword);
        session.save(producer);
        session.close();
    }

    @Override
    public void updatePicture(String newPicture, long id) {
        Session session = sessionFactory.openSession();
        Producer producer = session.get(Producer.class, id);
        producer.setPicture(newPicture);
        session.update(producer);
        session.close();
    }

    @Override
    public void updateDescription(String newDescription, long id) {
        Session session = sessionFactory.openSession();
        Producer producer = session.get(Producer.class, id);
        producer.setDescription(newDescription);
        session.update(producer);
        session.close();
    }

    @Override
    public void updateAddress(Address address, long id) {
        Session session = sessionFactory.openSession();
        Producer producer = session.get(Producer.class, id);
        producer.setAddress(address);
        session.update(producer);
        session.close();
    }

    @Transactional(readOnly = true)
    @Override
    public Producer getById(long id) {
        Session session = sessionFactory.openSession();
        Producer producer = session.get(Producer.class, id);
        session.close();
        return producer;
    }


    @Override
    public boolean isExistById(long id) {
        Session session = sessionFactory.openSession();
        Optional<Producer> optionalProducer = session.createQuery(GET_BY_ID, Producer.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        session.close();
        return optionalProducer.isPresent();
    }

    @Override
    public boolean isExistByEmail(String email) {
        Session session = sessionFactory.openSession();
        Optional<Producer> optionalProducer = session.createQuery(GET_BY_EMAIL, Producer.class)
                .setParameter("email", email)
                .uniqueResultOptional();
        session.close();
        return optionalProducer.isPresent();
    }
}
