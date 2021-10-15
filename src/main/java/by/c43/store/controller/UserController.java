package by.c43.store.controller;


import by.c43.store.dto.usersDTO.*;
import by.c43.store.entity.TypeOfUser;
import by.c43.store.entity.User;
import by.c43.store.service.ProductBasket;
import by.c43.store.service.UserService;
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
      private  UserService userService;
      private  ProductBasket productBasket;


    @GetMapping("/reg")
    public String registration(Model model){
        model.addAttribute("userRegDTO", new AllArgUsersDTO());
        return "reg";
    }


    @PostMapping("/reg")
    public String registration(@Valid @ModelAttribute("userRegDTO")AllArgUsersDTO userDTO, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            User user = ConverterOfDTO.getAllArgUsersDTO(userDTO);
            if (userService.registration(user)) {
                model.addAttribute("message1", "Registration successful!");
            } else {
                model.addAttribute("message2", "Email already exists!");
            }
        }
        return "reg";
    }

    @GetMapping("/auth")
    public String authorization(Model model){
        model.addAttribute("userAuthDTO", new EmailPasswordUsersDTO());
        return "auth";
    }


    @PostMapping("/auth")
    public String authorization(@Valid @ModelAttribute("userAuthDTO")EmailPasswordUsersDTO userDTOauth, BindingResult bindingResult, Model model, HttpSession httpSession){
        if (!bindingResult.hasErrors()) {
            User user = ConverterOfDTO.getEmailPasswordUsersDTO(userDTOauth);
            Optional<User> userOp= userService.authorization(user);
            if (userOp.isPresent()) {
                httpSession.setAttribute("user", userOp.get());
                httpSession.setAttribute("basket", productBasket);
                model.addAttribute("messageAuth1", "Authorization was successful!");
            } else {
                model.addAttribute("messageAuth2", "User not found!");
            }
        }
        return "/auth";
    }


    @GetMapping("/updName")
    public String updateName(Model model){
        model.addAttribute("userNameDTO", new NameUserDTO());
        return "updName";
    }


    @PostMapping("/updName")
    public String updateName(@Valid @ModelAttribute("userNameDTO")NameUserDTO nameUserDTO , BindingResult bindingResult, Model model, HttpSession httpSession) {
       if(!bindingResult.hasErrors()) {
           User user = (User) httpSession.getAttribute("user");
           if (userService.updateUserName(user, nameUserDTO.getName())) {
               user.setName(nameUserDTO.getName());
               httpSession.setAttribute("user", user);
               model.addAttribute("messageName1", "Name has been changed!");
           } else {
               model.addAttribute("messageName2", "Name has not been changed!");
           }
       }
       return "updName";
    }



    @GetMapping("/updEmail")
    public String updateEmail(Model model){
        model.addAttribute("userEmailDTO", new EmailUserDTO());
        return "updEmail";
    }


    @PostMapping("/updEmail")
    public String updateEmail(@Valid @ModelAttribute("userEmailDTO")EmailUserDTO emailUserDTO, BindingResult bindingResult, Model model, HttpSession httpSession) {
        if(!bindingResult.hasErrors()) {
            User user = (User) httpSession.getAttribute("user");
            if (userService.updateUserEmail(user, emailUserDTO.getEmail())) {
                user.setName(emailUserDTO.getEmail());
                httpSession.setAttribute("user", user);
                model.addAttribute("messageEmail1", "Email has been changed!");
            } else {
                model.addAttribute("messageEmail2", "Email has not been changed!");
            }
        }
        return "updEmail";
    }



    @GetMapping("/updPassword")
    public String updatePassword(Model model){
        model.addAttribute("userPasswordDTO", new PasswordUserDTO());
        return "updPassword";
    }


    @PostMapping("/updPassword")
    public String updatePassword(@Valid @ModelAttribute("userPasswordDTO")PasswordUserDTO passwordUserDTO, BindingResult bindingResult, Model model, HttpSession httpSession) {
        if(!bindingResult.hasErrors()) {
            User user = (User) httpSession.getAttribute("user");
            if (userService.updateUserPassword(passwordUserDTO.getOldPassword(), passwordUserDTO.getNewPassword(), passwordUserDTO.getConfirmNewPassword(), user)) {
                user.setName(passwordUserDTO.getNewPassword());
                httpSession.setAttribute("user", user);
                model.addAttribute("messagePassword1", "Password has been changed!");
            } else {
                model.addAttribute("messagePassword2", "Password has not been changed!");
            }
        }
        return "updPassword";
    }



    @GetMapping("/updPicture")
    public String updatePicture(Model model){
        model.addAttribute("userPictureDTO", new PictureUserDTO());
        return "updPicture";
    }



    @PostMapping("/updPicture")
    public String updatePicture(@Valid @ModelAttribute("userPictureDTO") PictureUserDTO pictureUserDTO, BindingResult bindingResult, Model model, HttpSession httpSession) {
        if(!bindingResult.hasErrors()) {
            User user = (User) httpSession.getAttribute("user");
            if (userService.updateUserPicture(user, pictureUserDTO.getPicture())) {
                user.setName(pictureUserDTO.getPicture());
                httpSession.setAttribute("user", user);
                model.addAttribute("messagePicture1", "Picture has been changed!");
            } else {
                model.addAttribute("messagePicture2", "Picture has not been changed!");
            }
        }
        return "updPicture";
    }



    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable long id, Model model, HttpSession httpSession) {
         User user = (User) httpSession.getAttribute("user");
         if(userService.deleteUser(user, id)){
             model.addAttribute("messageDeleteUser1", "User has been deleted!");
         }else{
             model.addAttribute("messageDeleteUser2", "User has not been deleted!");
         }
         return "deleteUser";
    }



    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        productBasket = (ProductBasket) httpSession.getAttribute("basket");
        if(productBasket != null){
             productBasket.resetReservedStatusAfterLogOutOrPurchase();
        }
        httpSession.invalidate();
        return "redirect:/";
    }



}
