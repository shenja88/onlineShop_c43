package by.c43.store.dao;

import by.c43.store.entity.Telephone;
import by.c43.store.entity.User;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void save(User user);

    void delete(long id);

    void updateName(String newName, long id);

    void updateEmail(String newEmail,long id);

    void updatePassword(String newPassword,long id);

    void updatePicture(String newPicture,long id);

    void updateTelephone(long userId, Telephone telephone);

    Optional<User> getById(long id);

    User getByEmail(String email);

    boolean isExistById(long id);

    boolean isExistByEmail(String email);

    boolean isExistByEmailAndPassword(String email, String password);

    List<User> getAll();
}
