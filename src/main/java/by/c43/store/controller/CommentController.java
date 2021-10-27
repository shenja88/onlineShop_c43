package by.c43.store.controller;

import by.c43.store.dto.commentDTO.DescriptionIdCommentDTO;
import by.c43.store.dto.commentDTO.DescriptionProductDTO;
import by.c43.store.entity.Comment;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import by.c43.store.service.CommentService;
import by.c43.store.service.ProductService;
import by.c43.store.utils.ControllerMessageManager;
import by.c43.store.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ProductService productService;

    @GetMapping("/add/{id}")
    public String showListOfComments(@ModelAttribute("newComment") DescriptionProductDTO descriptionProductUserDTO, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        return "product/addComment";
    }

    @PostMapping("/add")
    public String addComment(@Valid @ModelAttribute("newComment") DescriptionProductDTO commentDTO, BindingResult bindingResult, Model model, HttpSession session
    ,long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message_add_com", ControllerMessageManager.ADD_COMM_FAIL);
        } else {
            User user = (User) session.getAttribute("user");
            commentDTO.setProductId(id);
            if (commentService.saveComment(ConverterOfDTO.getDescriptionProductDTO(commentDTO, user))) {
                model.addAttribute("message_add_com", ControllerMessageManager.ADD_COMM_SUCCESSFULLY);
            }
        }
        return "product/addComment";
    }

    @GetMapping("/update/{id}")
    public String showListOfCommentsForUpdate(@ModelAttribute("updatedComment") DescriptionIdCommentDTO commentDTO, @PathVariable String id, Model model) {
        model.addAttribute("comId", id);
        return "product/updateComment";
    }

    @PostMapping("/update")
    public String updateComments(@Valid @ModelAttribute("updatedComment") DescriptionIdCommentDTO commentDTO, BindingResult bindingResult,
                                 Model model, long id) {
        if (!bindingResult.hasErrors()) {
            commentDTO.setCommentId(id);
            if (commentService.updateDescription(commentDTO.getCommentId(), commentDTO.getDescription())) {
                model.addAttribute("message_update_com", ControllerMessageManager.UPDATE_COMM_SUCCESSFULLY);
            } else {
                model.addAttribute("message_update_com", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "product/updateComment";
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable long id, Model model) {
        if (commentService.deleteById(id)) {
            model.addAttribute("message_remove_com", ControllerMessageManager.DELETE_COMM_SUCCESSFULLY);
        } else {
            model.addAttribute("message_remove_com", ControllerMessageManager.OPERATION_FAILED);
        }
        return "store";
    }

    @GetMapping("/allByProduct/{id}")
    public String showAllCommentsByProductId(@PathVariable long id, Model model) {
        Optional<Product> byId = productService.getById(id);
        if(byId.isPresent()) {
            List<Comment> commentsByIdProduct = commentService.getCommentsByIdProduct(id);
            model.addAttribute("allComments", commentsByIdProduct);
            model.addAttribute("productById", byId.get());
            return "product/productComments";
        }else return "store";
    }

}
