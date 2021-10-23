package by.c43.store.service;


import by.c43.store.dao.UserDao;
import by.c43.store.entity.Telephone;
import by.c43.store.entity.TypeOfUser;
import by.c43.store.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserDao userDAO;


    public boolean registration(User user) {
        if (userDAO.isExistByEmail(user.getEmail())) {
            return false;
        } else {
            userDAO.save(user);
            return true;
        }
    }

    public Optional<User> authorization(User user) {
        if (userDAO.isExistByEmail(user.getEmail())) {
            return Optional.ofNullable(userDAO.getByEmail(user.getEmail()));
        }
        return Optional.empty();
    }

    public boolean updateUserName(User user, String newName) {
        if (userDAO.isExistById(user.getId())) {
            if (!user.getName().equals(newName)) {
                userDAO.updateName(newName, user.getId());
                return true;
            }
        }
        return false;
    }

    public boolean updateUserPassword(String oldPassword, String newPassword, String confirmPassword, User user) {
        if (checkPassword(user.getPassword(), oldPassword) && !oldPassword.equals(newPassword)) {
            if (checkPassword(newPassword, confirmPassword)) {
                userDAO.updatePassword(newPassword, user.getId());
                return true;
            }
        }
        return false;
    }

    public boolean updateUserEmail(User user, String newEmail) {
        if (user.getEmail().equals(newEmail)) {
            return false;
        } else {
            userDAO.updateEmail(newEmail, user.getId());
            return true;
        }
    }

    public boolean updateUserPicture(User user, String newPicture) {
        if (user.getPicture().equals(newPicture)) {
            return false;
        } else {
            userDAO.updatePicture(newPicture, user.getId());
            return true;
        }
    }

    public boolean updateTelephone(User user, Telephone telephone) {
        if (user.getTelephone().getNumber().equals(telephone.getNumber())) {
            return false;
        } else {
            userDAO.updateTelephone(user.getId(), telephone);
            return true;
        }
    }

    public boolean deleteUser(User user, long id) {
        if (userDAO.isExistById(id) && user.getTypeOfUser().equals(TypeOfUser.ADMIN)) {
            userDAO.delete(id);
            return true;
        }
        return false;
    }

    public Optional<User> getUserById(long id){
        return userDAO.getById(id);
    }

    private boolean checkPassword(String newPassword, String confirmPassword) {
        return newPassword.equals(confirmPassword);
    }

}
