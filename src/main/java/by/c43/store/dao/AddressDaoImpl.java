package by.c43.store.dao;

import by.c43.store.entity.Address;
import by.c43.store.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Component
public class AddressDaoImpl implements AddressDao {
    private final SessionFactory sessionFactory;

    public AddressDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static final String GET_BY_ID = "from Address where id =: id";
    private static final String GET_BY_INFO = "from Address where country =: country and city =: city and street =: street and home =: home";

    @Override
    public void save(Address address) {
        Session session = sessionFactory.openSession();
        session.save(address);
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Address address = session.get(Address.class, id);
        session.delete(address);
        session.close();
    }




    @Transactional(readOnly = true)
    @Override
    public Address getById(long id) {
        Session session = sessionFactory.openSession();
        Address address = session.get(Address.class, id);
        session.close();
        return address;
    }

    @Override
    public boolean isExistById(long id) {
        Session session = sessionFactory.openSession();
        Optional<Address> optionalAddress = session
                .createQuery(GET_BY_ID, Address.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        session.close();
        return optionalAddress.isPresent();
    }

    @Override
    public boolean isExistByInfo(Address address) {
        Session session = sessionFactory.openSession();
        Optional<Address> optionalAddress = session
                .createQuery(GET_BY_INFO, Address.class)
                .setParameter("country", address.getCountry())
                .setParameter("city", address.getCity())
                .setParameter("street", address.getStreet())
                .setParameter("home", address.getHome())
                .uniqueResultOptional();
        session.close();
        return optionalAddress.isPresent();
    }

    @Override
    public void update(Address address) {
        Session session = sessionFactory.openSession();
        Address addressUp = session.load(Address.class, address.getId());
        addressUp.setCountry(address.getCountry());
        addressUp.setCity(address.getCity());
        addressUp.setStreet(address.getStreet());
        addressUp.setHome(address.getHome());
        session.update(addressUp);
        session.close();
    }


}
