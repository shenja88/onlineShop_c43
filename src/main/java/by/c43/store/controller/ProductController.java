package by.c43.store.controller;

import by.c43.store.dto.productDTO.*;
import by.c43.store.entity.CategoryOfProduct;
import by.c43.store.entity.Producer;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;
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

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/add")
    public String addProduct(@ModelAttribute("product") AllArgsProductDTO dto) {
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
        Product product = productService.getById(id);
        if (productService.delete(user, product)) {
            model.addAttribute("messageRemoveProd", ControllerMessageManager.DELETE_PRODUCT_SUCCESSFULLY);
        } else {
            model.addAttribute("messageRemoveProd", ControllerMessageManager.DELETE_PRODUCT_FAIL);
        }
        return "store";
    }

    @GetMapping("/udpName/{id}")
    public String updateName(@ModelAttribute("nameDTO") NameProductDTO dto, @PathVariable long id) {
        return "updateNameProd";
    }

    @PostMapping("/updName/{id}")
    public String updateName(@Valid @ModelAttribute("nameDTO") NameProductDTO dto, BindingResult bindingResult,
                             Model model, @PathVariable long id) {
        if (!bindingResult.hasErrors()) {
            if (productService.updateName(dto.getName(), id)) {
                model.addAttribute("messageUpdName", ControllerMessageManager.UPDATE_PRODUCT_NAME_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdName", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updateNameProd";
    }

    @GetMapping("/udpDesc/{id}")
    public String updateDescription(@ModelAttribute("descDTO") DescriptionProductDTO dto, @PathVariable long id) {
        return "updateDescriptionProd";
    }

    @PostMapping("/updDesc/{id}")
    public String updateDescription(@Valid @ModelAttribute("descDTO") DescriptionProductDTO dto, BindingResult bindingResult,
                                    Model model, @PathVariable long id) {
        if (!bindingResult.hasErrors()) {
            if (productService.updateDescription(dto.getDescription(), id)) {
                model.addAttribute("messageUpdDescription", ControllerMessageManager.UPDATE_DESCRIPTION_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdDescription", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updateDescriptionProd";
    }

    @GetMapping("/udpPicture/{id}")
    public String updatePicture(@ModelAttribute("pictureDTO") PictureProductDTO dto, @PathVariable long id) {
        return "updatePictureProd";
    }

    @PostMapping("/updPicture/{id}")
    public String updatePicture(@Valid @ModelAttribute("pictureDTO") PictureProductDTO dto, BindingResult bindingResult,
                                Model model, @PathVariable long id) {
        if (!bindingResult.hasErrors()) {
            if (productService.updatePicture(dto.getPicture(), id)) {
                model.addAttribute("messageUpdPicture", ControllerMessageManager.UPDATE_PICTURE_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdPicture", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updatePictureProd";
    }

    @GetMapping("/udpType/{id}")
    public String updateType(@ModelAttribute("typeDTO") TypeProductDTO dto, @PathVariable long id) {
        return "updateTypeProd";
    }

    @PostMapping("/updType/{id}")
    public String updateType(@Valid @ModelAttribute("typeDTO") TypeProductDTO dto, BindingResult bindingResult,
                             Model model, @PathVariable long id) {
        if (!bindingResult.hasErrors()) {
            if (productService.updateTypeProduct(dto.getCategory(), id)) {
                model.addAttribute("messageUpdType", ControllerMessageManager.UPDATE_TYPE_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdType", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "updateTypeProd";
    }

    @GetMapping("/udpPrice/{id}")
    public String updatePrice(@PathVariable long id) {
        return "updatePriceProd";
    }

    @PostMapping("/updPrice/{id}")
    public String updatePrice(double price, Model model, @PathVariable long id) {
        if (productService.updatePrice(price, id)) {
            model.addAttribute("messageUpdPicture", ControllerMessageManager.UPDATE_PRICE_SUCCESSFULLY);
        } else {
            model.addAttribute("messageUpdPicture", ControllerMessageManager.OPERATION_FAILED);
        }
        return "updatePriceProd";
    }

    @GetMapping("/updRating/{id}")
    public String updateRating(@PathVariable long id){
        return "updRatingProd";
    }

    @PostMapping("/updRating/{id}")
    public String updateRating(@PathVariable long id, double newRating, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(productService.updateScore(newRating, id, user)){
            model.addAttribute("messageUpdRating", ControllerMessageManager.UPDATE_RATING_SUCCESSFULLY);
        } else{
            model.addAttribute("messageUpdRating", ControllerMessageManager.OPERATION_FAILED);
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

    @GetMapping("/all")
    public String getAll(Model model){
        model.addAttribute("listProd", productService.getAll());
        return "store";
    }

}
