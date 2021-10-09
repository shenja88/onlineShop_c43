package by.c43.store.dao;

import by.c43.store.entity.Address;

public interface AddressDao {

    void save(Address address);

    void delete(long id);

    Address getById(long id);

    boolean isExistById(long id);

    boolean isExistByInfo(Address address);
}
