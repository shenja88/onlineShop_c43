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
    public void delete(long id) {
        Address address = sessionFactory.getCurrentSession().get(Address.class, id);
        sessionFactory.getCurrentSession().delete(address);
    }

    @Transactional(readOnly = true)
    @Override
    public Address getById(long id) {
        Address address = sessionFactory.getCurrentSession().get(Address.class, id);
        return address;
    }

    @Override
    public boolean isExistById(long id) {
        Optional<Address> optionalAddress = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, Address.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        return optionalAddress.isPresent();
    }

    @Override
    public boolean isExistByInfo(Address address) {
        Optional<Address> optionalAddress = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_INFO, Address.class)
                .setParameter("country", address.getCountry())
                .setParameter("city", address.getCity())
                .setParameter("street", address.getStreet())
                .setParameter("home", address.getHome())
                .uniqueResultOptional();
        return optionalAddress.isPresent();
    }
}
