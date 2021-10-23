package by.c43.store.controller;

import by.c43.store.dto.cardDTO.CardDTO;
import by.c43.store.dto.productDTO.*;
import by.c43.store.entity.CategoryOfProduct;
import by.c43.store.entity.Producer;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CommentService commentService;

    @GetMapping("/add")
    public String addProduct(@ModelAttribute("product") AllArgsProductDTO dto, Model model) {
        model.addAttribute("categories", productService.getCategories());
        return "addProd";
    }

    @PostMapping("/add")
    public String addProduct(@Valid  @ModelAttribute("product") AllArgsProductDTO dto, BindingResult bindingResult,
                             HttpSession session, Model model) {
        if (!bindingResult.hasErrors()) {
            Producer producer = (Producer) session.getAttribute("producer");
            Product product = ConverterOfDTO.getAllArgsProductDTO(dto, producer);
            if (productService.save(product)) {
                model.addAttribute("messageAddProd", ControllerMessageManager.ADD_PRODUCT_SUCCESSFULLY);
            } else {
                model.addAttribute("messageAddProd", ControllerMessageManager.ADD_PRODUCT_FAIL);
            }
        }
        return "addProd";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Optional<Product> product = productService.getById(id);
        if(product.isPresent()) {
            if (productService.delete(user, product.get())) {
                model.addAttribute("messageRemoveProd", ControllerMessageManager.DELETE_PRODUCT_SUCCESSFULLY);
            } else {
                model.addAttribute("messageRemoveProd", ControllerMessageManager.DELETE_PRODUCT_FAIL);
            }
        }
        return "store";
    }

    @GetMapping("/udpName/{id}")
    public String updateName(@ModelAttribute("nameDTO") NameProductDTO dto, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        return "updateNameProd";
    }

    @PostMapping("/updName")
    public String updateName(@Valid @ModelAttribute("nameDTO") NameProductDTO dto, BindingResult bindingResult,
                             Model model) {
        if (!bindingResult.hasErrors()) {
            if (productService.updateName(dto.getName(), dto.getId())) {
                model.addAttribute("messageUpdName", ControllerMessageManager.UPDATE_PRODUCT_NAME_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdName", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updateNameProd";
    }

    @GetMapping("/udpDesc/{id}")
    public String updateDescription(@ModelAttribute("descDTO") DescriptionProductDTO dto, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        return "updateDescriptionProd";
    }

    @PostMapping("/updDesc")
    public String updateDescription(@Valid @ModelAttribute("descDTO") DescriptionProductDTO dto, BindingResult bindingResult,
                                    Model model) {
        if (!bindingResult.hasErrors()) {
            if (productService.updateDescription(dto.getDescription(), dto.getId())) {
                model.addAttribute("messageUpdDescription", ControllerMessageManager.UPDATE_DESCRIPTION_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdDescription", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updateDescriptionProd";
    }

    @GetMapping("/udpPicture/{id}")
    public String updatePicture(@ModelAttribute("pictureDTO") PictureProductDTO dto, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        return "updatePictureProd";
    }

    @PostMapping("/updPicture")
    public String updatePicture(@Valid @ModelAttribute("pictureDTO") PictureProductDTO dto, BindingResult bindingResult,
                                Model model) {
        if (!bindingResult.hasErrors()) {
            if (productService.updatePicture(dto.getPicture(), dto.getId())) {
                model.addAttribute("messageUpdPicture", ControllerMessageManager.UPDATE_PICTURE_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdPicture", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updatePictureProd";
    }

    @GetMapping("/udpType/{id}")
    public String updateType(@ModelAttribute("typeDTO") TypeProductDTO dto, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        model.addAttribute("category", productService.getCategories());
        return "updateTypeProd";
    }

    @PostMapping("/updType")
    public String updateType(@Valid @ModelAttribute("typeDTO") TypeProductDTO dto, BindingResult bindingResult,
                             Model model) {
        if (!bindingResult.hasErrors()) {
            if (productService.updateTypeProduct(dto.getCategory(), dto.getId())) {
                model.addAttribute("messageUpdType", ControllerMessageManager.UPDATE_TYPE_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdType", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updateTypeProd";
    }

    @GetMapping("/udpPrice/{id}")
    public String updatePrice(@ModelAttribute("newPrice") NewPriceDTO priceDTO, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        return "updatePriceProd";
    }

    @PostMapping("/updPrice")
    public String updatePrice(@Valid @ModelAttribute("newPrice") NewPriceDTO priceDTO, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()) {
            if (productService.updatePrice(priceDTO.getPrice(), priceDTO.getProdId())) {
                model.addAttribute("messageUpdPrice", ControllerMessageManager.UPDATE_PRICE_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdPrice", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updatePriceProd";
    }

    @GetMapping("/updRating/{id}")
    public String updateRating(@PathVariable long id, @ModelAttribute("newRating") NewRatingDTO ratingDTO, Model model){
        model.addAttribute("prodId", id);
        return "updRatingProd";
    }

    @PostMapping("/updRating")
    public String updateRating(@Valid @ModelAttribute("newRating") NewRatingDTO ratingDTO, BindingResult bindingResult, Model model, HttpSession session){
        if(!bindingResult.hasErrors()) {
            User user = (User) session.getAttribute("user");
            if (productService.updateScore(ratingDTO.getScore(), ratingDTO.getProdId(), user)) {
                model.addAttribute("messageUpdRating", ControllerMessageManager.UPDATE_RATING_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdRating", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updRatingProd";
    }

    @PostMapping("/byType")
    public String getByType(CategoryOfProduct category, Model model){
        List<Product> products = productService.getByCategory(category);
        model.addAttribute("listProd", products);
        return "store";
    }

    @PostMapping("/byOwner")
    public String getByOwner(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Product> products = productService.getByOwner(user);
        model.addAttribute("listProd", products);
        return "store";
    }

    @PostMapping("/byProducer")
    public String getByProducer(Model model, HttpSession session){
        Producer producer = (Producer) session.getAttribute("producer");
        List<Product> products = productService.getByProducer(producer);
        model.addAttribute("listProd", products);
        return "store";
    }

    @PostMapping("/byRating")
    public String getByRating(String low, String up, Model model){
        List<Product> products = new ArrayList<>();
        products = productService.chooseProductsByRating(low, up, products);
        model.addAttribute("listProd", products);
        return "store";
    }

    @PostMapping("/byPrice")
    public String getByPrice(String low, String up, Model model){
        List<Product> products = new ArrayList<>();
        products = productService.chooseProductsByPrice(low, up, products) ;
        model.addAttribute("listProd", products);
        return "store";
    }

    @GetMapping("/getProductById/{id}")
    public String getProductById(@PathVariable long id, Model model){
        Optional<Product> product = productService.getById(id);
        if(product.isPresent()){
            CardDTO cardDTO = CardDTO.builder()
                    .product(product.get())
                    .comments(commentService.getCommentsByIdProduct(id))
                    .build();
            model.addAttribute("card", cardDTO);
            return "product";
        }else return "store";
    }

    @GetMapping("/all")
    public String getAll(Model model){
        model.addAttribute("listProd", productService.getAll());
        return "store";
    }

    @GetMapping("/byProducer/{id}")
    public String getByProducer(Model model, @PathVariable long id){
        List<Product> products = productService.getByProducer(Producer.builder().id(id).build());
        model.addAttribute("producerProducts", products);
        return "producerProducts";
    }
}
