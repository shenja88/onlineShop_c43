package by.c43.store.controller;

import by.c43.store.dto.cardDTO.CardDTO;
import by.c43.store.dto.commentDTO.DescriptionProductUserDTO;
import by.c43.store.entity.Comment;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import by.c43.store.service.CommentService;
import by.c43.store.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @GetMapping("/add{id}")

    public String showListOfComments(@PathVariable long idProduct, @ModelAttribute("newComment") DescriptionProductUserDTO descriptionProductUserDTO, Model model){
        List<Comment> commentsByIdProduct = commentService.getCommentsByIdProduct(idProduct);
        model.addAttribute("commentsByIdProduct", commentsByIdProduct);
        return "comment";
    }

    @PostMapping("/add")
    public String addComment(@Valid @ModelAttribute("newComment") DescriptionProductUserDTO commentDTO, BindingResult bindingResult, Model model, HttpSession httpSession){
        if (bindingResult.hasErrors()){
            model.addAttribute("message_add_com", "Comment adding error");
            return "comment";
        }else{
            commentService.saveComment(ConverterOfDTO.getDescriptionProductUserDTO(commentDTO));
            model.addAttribute("message_add_com", "Comment added!");
            return "comment";
        }
    }

    @GetMapping("/update")
    public String showListOfCommentsForUpdate(@ModelAttribute("updatedComment") DescriptionProductUserDTO commentDTO){
        return "comment";
    }

    @PostMapping("/update")
    public String updateComments(@ModelAttribute("updatedComment") DescriptionProductUserDTO commentDTO, String newDescription, HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("user");
        if (commentService.updateDescription(user.getId(), newDescription)){
            model.addAttribute("message_update_com", "Successfully updated!");
        }else {
            model.addAttribute("message_update_com", "Operation failed");
        }
        return "comment";
    }

    @PostMapping("/delete/{id}")
    public String deleteComment(@PathVariable long id, @ModelAttribute("deletedComment") DescriptionProductUserDTO commentDTO, HttpSession httpSession, Model model){
        if (commentService.deleteById(id)){
            model.addAttribute("message_remove_com", "Successfully deleted!");
        }else{
            model.addAttribute("message_remove_com", "Operation failed");
        }
        return "comment";
    }

    @GetMapping("/allByProduct/{id}")
    public String showAllCommentsByProductId (@PathVariable long id, @ModelAttribute("allComments") DescriptionProductUserDTO commentDTO, HttpSession httpSession, Model model){
        List<Comment> commentsByIdProduct = commentService.getCommentsByIdProduct(id);
        model.addAttribute("allComments", commentsByIdProduct);
        return "comment";
    }

}
