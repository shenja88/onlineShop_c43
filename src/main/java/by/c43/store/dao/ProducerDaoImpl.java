package by.c43.store.dao;

import by.c43.store.entity.Address;
import by.c43.store.entity.Producer;
import by.c43.store.entity.Telephone;
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
    private  static final String GET_BY_EMAIL_PASS = "from Producer where email =: email and password =: password";


    private final SessionFactory sessionFactory;

    public ProducerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProducer(Producer producer) {
        sessionFactory.getCurrentSession().save(producer);
    }

    @Override
    public void deleteProducer(long id) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, id);
        sessionFactory.getCurrentSession().delete(producer);
    }

    @Override
    public void updateName(String newName, long id) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, id);
        producer.setName(newName);
        sessionFactory.getCurrentSession().update(producer);
    }

    @Override
    public void updateEmail(String newEmail, long id) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, id);
        producer.setEmail(newEmail);
        sessionFactory.getCurrentSession().update(producer);
    }

    @Override
    public void updatePassword(String newPassword, long id) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, id);
        producer.setPassword(newPassword);
        sessionFactory.getCurrentSession().save(producer);
    }

    @Override
    public void updatePicture(String newPicture, long id) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, id);
        producer.setPicture(newPicture);
        sessionFactory.getCurrentSession().update(producer);
    }

    @Override
    public void updateDescription(String newDescription, long id) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, id);
        producer.setDescription(newDescription);
        sessionFactory.getCurrentSession().update(producer);
    }

    @Override
    public void updateAddress(Address address, long id) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, id);
        producer.getAddress().setCountry(address.getCountry());
        producer.getAddress().setCity(address.getCity());
        producer.getAddress().setStreet(address.getStreet());
        producer.getAddress().setHome(address.getHome());
        sessionFactory.getCurrentSession().update(producer);
    }

    @Transactional(readOnly = true)
    @Override
    public Producer getById(long id) {
        return sessionFactory.getCurrentSession().get(Producer.class, id);
    }

    @Override
    public Optional<Producer> getByEmailAndPass(String email, String password) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_EMAIL_PASS, Producer.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResultOptional();
    }

    @Override
    public void updateTelephone(long producerId, Telephone telephone) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, producerId);
        producer.getTelephones().get(producer.getTelephones().indexOf(telephone)).setNumber(telephone.getNumber());
        sessionFactory.getCurrentSession().update(producer);
    }

    @Override
    public void addTelephone(long producerId, Telephone telephone) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, producerId);
        producer.getTelephones().add(telephone);
        sessionFactory.getCurrentSession().update(producer);
    }


    @Override
    public boolean isExistById(long id) {
        Optional<Producer> optionalProducer = sessionFactory.getCurrentSession().createQuery(GET_BY_ID, Producer.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        return optionalProducer.isPresent();
    }

    @Override
    public boolean isExistByEmail(String email) {
        Optional<Producer> optionalProducer = sessionFactory.getCurrentSession().createQuery(GET_BY_EMAIL, Producer.class)
                .setParameter("email", email)
                .uniqueResultOptional();
        return optionalProducer.isPresent();
    }

    @Override
    public void deleteTelephone(Telephone telephone, long id) {
        Producer producer = sessionFactory.getCurrentSession().get(Producer.class, id);
        producer.getTelephones().remove(telephone);
    }
}
