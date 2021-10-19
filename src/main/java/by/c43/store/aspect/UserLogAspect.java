package by.c43.store.aspect;

import by.c43.store.dto.telephonesDTO.NumberTelDTO;
import by.c43.store.dto.usersDTO.*;
import by.c43.store.entity.User;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

@Aspect
public class UserLogAspect {

    private final Logger logger = LoggerFactory.getLogger(UserLogAspect.class.getSimpleName());

    @Pointcut("execution(public * by.c43.store.controller.UserController.registration(..)) && args(userDTO, *, ..)")
    public void registration(AllArgUsersDTO userDTO) {}

    @Pointcut("execution(public * by.c43.store.controller.UserController.authorization(..)) && args(userDTOauth, *, ..)")
    public void authorization(EmailPasswordUsersDTO userDTOauth){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updateName(..)) && args(nameUserDTO, *, ..)")
    public void updateName(NameUserDTO nameUserDTO){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updateEmail(..)) && args(emailUserDTO, *, ..)")
    public void updateEmail(EmailUserDTO emailUserDTO){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updatePassword(..)) && args(passwordUserDTO, *, ..)")
    public void updatePassword(PasswordUserDTO passwordUserDTO){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updatePicture(..)) && args(pictureUserDTO, *, ..)")
    public void updatePicture(PictureUserDTO pictureUserDTO){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updateNumber(..)) && args(telDTO, *, ..)")
    public void updateNumber(NumberTelDTO telDTO){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.deleteUser(..)) && args(id, ..)")
    public void delete(long id){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.logout(..)) && args(httpSession, ..)")
    public void logOut(HttpSession httpSession){}

    @After(value = "registration(userDTO)", argNames = "userDTO")
    public void reg(AllArgUsersDTO userDTO){
        logger.info("User with login {} registered.", userDTO.getEmail() );
    }

    @After(value = "authorization(userDTOauth)", argNames = "userDTOauth")
    public void auth(EmailPasswordUsersDTO userDTOauth){
        logger.info("User with login {} authorized", userDTOauth.getEmail());
    }

    @After(value = "updateName(nameUserDTO)", argNames = "nameUserDTO")
    public void upName(NameUserDTO nameUserDTO){
        logger.info("The current user updated name on {}.", nameUserDTO.getName());
    }

    @After(value = "updateEmail(emailUserDTO)", argNames = "emailUserDTO")
    public void upEmail(EmailUserDTO emailUserDTO){
        logger.info("The current user updated email.");
    }

    @After(value = "updatePassword(passwordUserDTO)", argNames = "passwordUserDTO")
    public void upPassword(PasswordUserDTO passwordUserDTO){
        logger.info("The current user updated password.");
    }

    @After(value = "updatePicture(pictureUserDTO)", argNames = "pictureUserDTO")
    public void upPicture(PictureUserDTO pictureUserDTO){
        logger.info("The current user updated picture with URL - {}.", pictureUserDTO.getPicture());
    }

    @After(value = "updateNumber(telDTO)", argNames = "telDTO")
    public void upNumber(NumberTelDTO telDTO){
        logger.info("The current user updated number to {}.", telDTO.getNumber());
    }

    @After(value = "delete(id)", argNames = "id")
    public void del(long id){
        logger.info("User with id ({}) deleted.", id);
    }

    @Before(value = "logOut(httpSession)", argNames = "httpSession")
    public void out(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        logger.info("User with email ({}) out!", user.getEmail());
    }
}
