package by.c43.store.dao;

import by.c43.store.entity.Telephone;

import java.util.List;

public interface TelephoneDao {


    void save(Telephone telephone);

    void delete(long id);

    Telephone getById(long id);

    boolean isExistById(long id);

    boolean isExistByNumber(String number);
}
