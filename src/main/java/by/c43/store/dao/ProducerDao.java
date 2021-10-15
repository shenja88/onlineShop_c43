package by.c43.store.dao;

import by.c43.store.entity.Address;
import by.c43.store.entity.Producer;
import by.c43.store.entity.Telephone;

public interface ProducerDao {

    void addProducer(Producer producer);

    void deleteProducer(long id);

    void updateName(String newName, long id);

    void updateEmail(String newEmail, long id);

    void updatePassword(String newPassword, long id);

    void updatePicture(String newPicture, long id);

    void updateDescription(String newDescription, long id);

    void updateAddress(Address address, long id);

    Producer getById(long id);

    void updateTelephone(long producerId, Telephone telephone);

    void addTelephone(long producerId, Telephone telephone);

    boolean isExistById(long id);

    boolean isExistByEmail(String email);

}
