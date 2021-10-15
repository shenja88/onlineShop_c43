package by.c43.store.dao;

import by.c43.store.entity.Telephone;

public interface TelephoneDao {

    void delete(long id);

    Telephone getById(long id);

    boolean isExistById(long id);

    boolean isExistByNumber(String number);
}
