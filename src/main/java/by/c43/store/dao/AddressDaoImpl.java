package by.c43.store.dao;

import by.c43.store.entity.Address;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Component
public class AddressDaoImpl implements AddressDao{
    private final SessionFactory sessionFactory;

    public AddressDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static final String GET_BY_ID = "from Address where id =: id";
    private static final String GET_BY_INFO = "from Address where country =: country and city =: city and street =: street and home =: home";

    @Override
    public void save(Address address) {
        try (Session session = sessionFactory.openSession()) {
            session.save(address);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            Address address = session
                    .createQuery(GET_BY_ID, Address.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.delete(address);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address getById(long id) {
        Address address = null;
        try (Session session = sessionFactory.openSession()) {
            address = session
                    .createQuery(GET_BY_ID, Address.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public boolean isExistById(long id) {
        Optional<Address> optionalAddress = Optional.empty();
        try (Session session = sessionFactory.openSession()) {
            optionalAddress = session
                    .createQuery(GET_BY_ID, Address.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return optionalAddress.isPresent();
    }

    @Override
    public boolean isExistByInfo(Address address) {
        Optional<Address> optionalAddress = Optional.empty();
        try (Session session = sessionFactory.openSession()) {
            optionalAddress = session
                    .createQuery(GET_BY_INFO, Address.class)
                    .setParameter("country", address.getCountry())
                    .setParameter("city", address.getCity())
                    .setParameter("street", address.getStreet())
                    .setParameter("home", address.getHome())
                    .uniqueResultOptional();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return optionalAddress.isPresent();
    }
}
