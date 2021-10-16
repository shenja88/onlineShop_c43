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
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

@Aspect
public class UserAspect {

    private final Logger logger = LoggerFactory.getLogger(UserAspect.class);

    @Pointcut("execution(public * by.c43.store.controller.UserController.registration(..)) && args(userDTO, bindingResult, ..)")
    public void registration(AllArgUsersDTO userDTO, BindingResult bindingResult) {}

    @Pointcut("execution(public * by.c43.store.controller.UserController.authorization(..)) && args(userDTOauth, bindingResult, ..)")
    public void authorization(EmailPasswordUsersDTO userDTOauth, BindingResult bindingResult){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updateName(..)) && args(nameUserDTO, bindingResult, ..)")
    public void updateName(NameUserDTO nameUserDTO, BindingResult bindingResult){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updateEmail(..)) && args(emailUserDTO, bindingResult, ..)")
    public void updateEmail(EmailUserDTO emailUserDTO, BindingResult bindingResult){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updatePassword(..)) && args(passwordUserDTO, bindingResult, ..)")
    public void updatePassword(PasswordUserDTO passwordUserDTO, BindingResult bindingResult){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updatePicture(..)) && args(pictureUserDTO, bindingResult, ..)")
    public void updatePicture(PictureUserDTO pictureUserDTO, BindingResult bindingResult){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.updateNumber(..)) && args(telDTO, bindingResult, ..)")
    public void updateNumber(NumberTelDTO telDTO, BindingResult bindingResult){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.deleteUser(..)) && args(id, ..)")
    public void delete(long id){}

    @Pointcut("execution(public * by.c43.store.controller.UserController.logout(..)) && args(httpSession, ..)")
    public void logOut(HttpSession httpSession){}

    @After(value = "registration(userDTO, bindingResult)", argNames = "userDTO, bindingResult")
    public void reg(AllArgUsersDTO userDTO, BindingResult bindingResult){
        logger.info("User with login {} registered", userDTO.getEmail() );
    }

    @After(value = "authorization(userDTOauth, bindingResult)", argNames = "userDTOauth, bindingResult")
    public void auth(EmailPasswordUsersDTO userDTOauth, BindingResult bindingResult){
        logger.info("User with login {} authorized", userDTOauth.getEmail());
    }

    @After(value = "updateName(nameUserDTO, bindingResult)", argNames = "nameUserDTO, bindingResult")
    public void upName(NameUserDTO nameUserDTO, BindingResult bindingResult){
        logger.info("The current user updated name on {}", nameUserDTO.getName());
    }

    @After(value = "updateEmail(emailUserDTO, bindingResult)", argNames = "emailUserDTO, bindingResult")
    public void upEmail(EmailUserDTO emailUserDTO, BindingResult bindingResult){
        logger.info("The current user updated email");
    }


    @After(value = "updatePassword(passwordUserDTO, bindingResult)", argNames = "passwordUserDTO, bindingResult")
    public void upPassword(PasswordUserDTO passwordUserDTO, BindingResult bindingResult){
        logger.info("The current user updated password");
    }

    @After(value = "updatePicture(pictureUserDTO, bindingResult)", argNames = "pictureUserDTO, bindingResult")
    public void upPicture(PictureUserDTO pictureUserDTO, BindingResult bindingResult){
        logger.info("The current user updated picture with URL - {}", pictureUserDTO.getPicture());
    }

    @After(value = "updateNumber(telDTO, bindingResult)", argNames = "telDTO, bindingResult")
    public void upNumber(NumberTelDTO telDTO, BindingResult bindingResult){
        logger.info("The current user updated number to {}", telDTO.getNumber());
    }

    @After(value = "delete(id)", argNames = "id")
    public void del(long id){
        logger.info("User with id ({}) deleted", id);
    }

    @Before(value = "logOut(httpSession)", argNames = "httpSession")
    public void out(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        logger.info("User with email ({}) out!", user.getEmail());
    }
}
