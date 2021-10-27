package by.c43.store.controller;


import by.c43.store.dto.telephonesDTO.NumberIdTelDTO;
import by.c43.store.dto.cardDTO.UserCardInfoDTO;
import by.c43.store.dto.usersDTO.*;
import by.c43.store.entity.Telephone;
import by.c43.store.entity.TypeOfUser;
import by.c43.store.entity.User;
import by.c43.store.service.ProductBasket;
import by.c43.store.service.UserService;
import by.c43.store.utils.ControllerMessageManager;
import by.c43.store.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private ProductBasket productBasket;

    @GetMapping("/reg")
    public String registration(Model model) {
        model.addAttribute("userRegDTO", new AllArgUsersDTO());
        return "user/reg";
    }

    @PostMapping("/reg")
    public String registration(@Valid @ModelAttribute("userRegDTO") AllArgUsersDTO userDTO,
                               BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            User user = ConverterOfDTO.getAllArgUsersDTO(userDTO);
            user.setTypeOfUser(TypeOfUser.USER);
            if (userService.registration(user)) {
                model.addAttribute("messageReg", ControllerMessageManager.REG_SUCCESSFULLY);
            } else {
                model.addAttribute("messageReg", ControllerMessageManager.REG_FAIL);
            }
        }
        return "user/reg";
    }

    @GetMapping("/auth")
    public String authorization(Model model) {
        model.addAttribute("userAuthDTO", new EmailPasswordUsersDTO());
        return "user/auth";
    }

    @PostMapping("/auth")
    public String authorization(@Valid @ModelAttribute("userAuthDTO") EmailPasswordUsersDTO userDTOauth,
                                BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (!bindingResult.hasErrors()) {
            User user = ConverterOfDTO.getEmailPasswordUsersDTO(userDTOauth);
            Optional<User> userOp = userService.authorization(user);
            if (userOp.isPresent()) {
                httpSession.setAttribute("user", userOp.get());
                if (userOp.get().getTypeOfUser().equals(TypeOfUser.ADMIN)) {
                    return "admin/adminPage";
                } else {
                    httpSession.setAttribute("basket", productBasket);
                    model.addAttribute("messageAuth", ControllerMessageManager.AUTH_SUCCESSFULLY);
                }
            } else {
                model.addAttribute("messageAuth", ControllerMessageManager.AUTH_FAIL);
            }
        }
        return "user/auth";
    }

    @GetMapping("/updName")
    public String updateName(Model model) {
        model.addAttribute("userNameDTO", new NameUserDTO());
        return "user/updName";
    }

    @PostMapping("/updName")
    public String updateName(@Valid @ModelAttribute("userNameDTO") NameUserDTO nameUserDTO,
                             BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (!bindingResult.hasErrors()) {
            User user = (User) httpSession.getAttribute("user");
            if (userService.updateUserName(user, nameUserDTO.getName())) {
                user.setName(nameUserDTO.getName());
                model.addAttribute("messageName", ControllerMessageManager.UPDATE_NAME_SUCCESSFULLY);
            } else {
                model.addAttribute("messageName", ControllerMessageManager.UPDATE_NAME_FAIL);
            }
        }
        return "user/updName";
    }

    @GetMapping("/updEmail")
    public String updateEmail(Model model) {
        model.addAttribute("userEmailDTO", new EmailUserDTO());
        return "user/updEmail";
    }

    @PostMapping("/updEmail")
    public String updateEmail(@Valid @ModelAttribute("userEmailDTO") EmailUserDTO emailUserDTO,
                              BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (!bindingResult.hasErrors()) {
            User user = (User) httpSession.getAttribute("user");
            if (userService.updateUserEmail(user, emailUserDTO.getEmail())) {
                user.setEmail(emailUserDTO.getEmail());
                model.addAttribute("messageEmail", ControllerMessageManager.UPDATE_EMAIL_SUCCESSFULLY);
            } else {
                model.addAttribute("messageEmail", ControllerMessageManager.UPDATE_EMAIL_FAIL);
            }
        }
        return "user/updEmail";
    }

    @GetMapping("/updPassword")
    public String updatePassword(Model model) {
        model.addAttribute("userPasswordDTO", new PasswordUserDTO());
        return "user/updPassword";
    }

    @PostMapping("/updPassword")
    public String updatePassword(@Valid @ModelAttribute("userPasswordDTO") PasswordUserDTO passwordUserDTO,
                                 BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (!bindingResult.hasErrors()) {
            User user = (User) httpSession.getAttribute("user");
            if (userService.updateUserPassword(passwordUserDTO.getOldPassword(), passwordUserDTO.getNewPassword(), passwordUserDTO.getConfirmNewPassword(), user)) {
                user.setPassword(passwordUserDTO.getNewPassword());
                model.addAttribute("messagePassword", ControllerMessageManager.UPDATE_PASSWORD_SUCCESSFULLY);
            } else {
                model.addAttribute("messagePassword", ControllerMessageManager.UPDATE_PASSWORD_FAIL);
            }
        }
        return "user/updPassword";
    }

    @GetMapping("/updPicture")
    public String updatePicture(Model model) {
        model.addAttribute("userPictureDTO", new PictureUserDTO());
        return "user/updPicture";
    }

    @PostMapping("/updPicture")
    public String updatePicture(@Valid @ModelAttribute("userPictureDTO") PictureUserDTO pictureUserDTO,
                                BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (!bindingResult.hasErrors()) {
            User user = (User) httpSession.getAttribute("user");
            if (userService.updateUserPicture(user, pictureUserDTO.getPicture())) {
                user.setPicture(pictureUserDTO.getPicture());
                model.addAttribute("messagePicture", ControllerMessageManager.UPDATE_PICTURE_SUCCESSFULLY);
            } else {
                model.addAttribute("messagePicture", ControllerMessageManager.UPDATE_PICTURE_FAIL);
            }
        }
        return "user/updPicture";
    }

    @GetMapping("/updTel/{id}")
    public String updateNumber(@PathVariable long id, Model model) {
        model.addAttribute("numberTelDTO", new NumberIdTelDTO());
        model.addAttribute("telId", id);
        return "user/updNumber";
    }

    @PostMapping("/updTel")
    public String updateNumber(@Valid @ModelAttribute("numberTelDTO") NumberIdTelDTO telDTO,
                               BindingResult bindingResult, Model model, HttpSession session) {
        if (!bindingResult.hasErrors()) {
            User user = (User) session.getAttribute("user");
            Telephone telephone = ConverterOfDTO.getIdTelIdDTO(telDTO);
            if (userService.updateTelephone(user, telephone)) {
                user.getTelephone().setNumber(telephone.getNumber());
                model.addAttribute("messageUpdNumber", ControllerMessageManager.UPDATE_NUMBER_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdNumber", ControllerMessageManager.UPDATE_NUMBER_FAIL);
            }
        }
        return "user/updNumber";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable long id, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (userService.deleteUser(user, id)) {
            model.addAttribute("message", ControllerMessageManager.DELETE_USER_SUCCESSFULLY);
        } else {
            model.addAttribute("message", ControllerMessageManager.DELETE_USER_FAIL);
        }
        return "admin/adminPage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        productBasket = (ProductBasket) httpSession.getAttribute("basket");
        if (productBasket != null) {
            productBasket.resetReservedStatusAfterLogOutOrPurchase();
        }
        httpSession.invalidate();
        return "redirect:/home";
    }

    @GetMapping("account")
    public String account() {
        return "user/account";
    }

    @GetMapping("/userInfo/{id}")
    public String getUserInfo(@PathVariable long id, Model model) {
        if (userService.getUserById(id).isPresent()) {
            UserCardInfoDTO userCard = ConverterOfDTO.getUserCard(userService.getUserById(id).get());
            model.addAttribute("userCard", userCard);
        } else {
            model.addAttribute("messageUserCard", ControllerMessageManager.USER_NOT_FOUND);
        }
        return "user/userInfo";
    }

    @GetMapping("/all")
    public String getAllForUser(Model model){
        model.addAttribute("allUser", userService.getAll());
        return "admin/adminLists";
    }
}
