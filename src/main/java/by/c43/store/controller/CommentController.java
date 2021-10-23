package by.c43.store.controller;

import by.c43.store.dto.commentDTO.DescriptionIdCommentDTO;
import by.c43.store.dto.commentDTO.DescriptionProductUserDTO;
import by.c43.store.entity.Comment;
import by.c43.store.entity.Product;
import by.c43.store.service.CommentService;
import by.c43.store.service.ProductService;
import by.c43.store.utils.ControllerMessageManager;
import by.c43.store.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ProductService productService;

    @GetMapping("/add")
    public String showListOfComments(@ModelAttribute("newComment") DescriptionProductUserDTO descriptionProductUserDTO) {
        return "addComment";
    }

    @PostMapping("/add")
    public String addComment(@Valid @ModelAttribute("newComment") DescriptionProductUserDTO commentDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message_add_com", ControllerMessageManager.ADD_COMM_FAIL);
        } else {
            if (commentService.saveComment(ConverterOfDTO.getDescriptionProductUserDTO(commentDTO))) {
                model.addAttribute("message_add_com", ControllerMessageManager.ADD_COMM_SUCCESSFULLY);
            }
        }
        return "addComment";
    }

    @GetMapping("/update/{id}")
    public String showListOfCommentsForUpdate(@ModelAttribute("updatedComment") DescriptionProductUserDTO commentDTO, @PathVariable String id, Model model) {
        model.addAttribute("comId", id);
        return "updateComment";
    }

    @PostMapping("/update")
    public String updateComments(@Valid @ModelAttribute("updatedComment") DescriptionIdCommentDTO commentDTO, BindingResult bindingResult,
                                 String newDescription, Model model) {
        if (!bindingResult.hasErrors()) {
            if (commentService.updateDescription(commentDTO.getCommentId(), newDescription)) {
                model.addAttribute("message_update_com", ControllerMessageManager.UPDATE_COMM_SUCCESSFULLY);
            } else {
                model.addAttribute("message_update_com", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updateComment";
    }

    @PostMapping("/delete/{id}")
    public String deleteComment(@PathVariable long id, Model model) {
        if (commentService.deleteById(id)) {
            model.addAttribute("message_remove_com", ControllerMessageManager.DELETE_COMM_SUCCESSFULLY);
        } else {
            model.addAttribute("message_remove_com", ControllerMessageManager.OPERATION_FAILED);
        }
        return "comment";
    }

    @GetMapping("/allByProduct/{id}")
    public String showAllCommentsByProductId(@PathVariable long id, Model model) {
        List<Comment> commentsByIdProduct = commentService.getCommentsByIdProduct(id);
        model.addAttribute("allComments", commentsByIdProduct);
        Product byId = productService.getById(id);
        model.addAttribute("productById", byId);
        return "comment";
    }

}
