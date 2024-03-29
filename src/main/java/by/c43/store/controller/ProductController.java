package by.c43.store.controller;

import by.c43.store.dto.cardDTO.CardDTO;
import by.c43.store.dto.productDTO.*;
import by.c43.store.entity.*;
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
    public String addProduct(Model model, @ModelAttribute("product") AllArgsProductDTO dto) {
        model.addAttribute("categories", productService.getCategories());
        return "product/addProd";
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
        return "product/addProd";
    }

    @GetMapping("/deleteForUser/{id}")
    public String deleteForUser(@PathVariable long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Optional<Product> product = productService.getById(id);
        if(product.isPresent()) {
            if (productService.deleteForUser(user, product.get())) {
                model.addAttribute("message", ControllerMessageManager.DELETE_PRODUCT_SUCCESSFULLY);
            } else {
                model.addAttribute("message", ControllerMessageManager.DELETE_PRODUCT_FAIL);
            }
        }
        if(user.getTypeOfUser().equals(TypeOfUser.ADMIN)){
            return "admin/adminPage";
        }
        return "store";
    }

    @GetMapping("/deleteForProducer/{id}")
    public String deleteForProducer(@PathVariable long id, Model model, HttpSession session) {
        Producer producer = (Producer) session.getAttribute("producer");
        Optional<Product> product = productService.getById(id);
        if(product.isPresent()) {
            if (productService.deleteForProducer(producer, product.get())) {
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
        return "product/updateNameProd";
    }

    @PostMapping("/updName")
    public String updateName(@Valid @ModelAttribute("nameDTO") NameProductDTO dto, BindingResult bindingResult,
                             Model model, long id) {
        if (!bindingResult.hasErrors()) {
            dto.setId(id);
            if (productService.updateName(dto.getName(), dto.getId())) {
                model.addAttribute("messageUpdName", ControllerMessageManager.UPDATE_PRODUCT_NAME_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdName", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "product/updateNameProd";
    }

    @GetMapping("/udpDesc/{id}")
    public String updateDescription(@ModelAttribute("descDTO") DescriptionProductDTO dto, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        return "product/updateDescriptionProd";
    }

    @PostMapping("/updDesc")
    public String updateDescription(@Valid @ModelAttribute("descDTO") DescriptionProductDTO dto, BindingResult bindingResult,
                                    Model model, long id) {
        if (!bindingResult.hasErrors()) {
            dto.setId(id);
            if (productService.updateDescription(dto.getDescription(), dto.getId())) {
                model.addAttribute("messageUpdDescription", ControllerMessageManager.UPDATE_DESCRIPTION_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdDescription", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "product/updateDescriptionProd";
    }

    @GetMapping("/udpPicture/{id}")
    public String updatePicture(@ModelAttribute("pictureDTO") PictureProductDTO dto, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        return "product/updatePictureProd";
    }

    @PostMapping("/updPicture")
    public String updatePicture(@Valid @ModelAttribute("pictureDTO") PictureProductDTO dto, BindingResult bindingResult,
                                Model model, long id) {
        if (!bindingResult.hasErrors()) {
            dto.setId(id);
            if (productService.updatePicture(dto.getPicture(), dto.getId())) {
                model.addAttribute("messageUpdPicture", ControllerMessageManager.UPDATE_PICTURE_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdPicture", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "product/updatePictureProd";
    }

    @GetMapping("/udpType/{id}")
    public String updateType(@ModelAttribute("typeDTO") TypeProductDTO dto, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        model.addAttribute("category", productService.getCategories());
        return "product/updateTypeProd";
    }

    @PostMapping("/updType")
    public String updateType(@Valid @ModelAttribute("typeDTO") TypeProductDTO dto, BindingResult bindingResult,
                             Model model, long id) {
        if (!bindingResult.hasErrors()) {
            dto.setId(id);
            if (productService.updateTypeProduct(dto.getCategory(), dto.getId())) {
                model.addAttribute("messageUpdType", ControllerMessageManager.UPDATE_TYPE_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdType", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "product/updateTypeProd";
    }

    @GetMapping("/updPrice/{id}")
    public String updatePrice(@ModelAttribute("newPrice") NewPriceDTO priceDTO, @PathVariable long id, Model model) {
        model.addAttribute("prodId", id);
        return "product/updatePriceProd";
    }

    @PostMapping("/updPrice")
    public String updatePrice(@Valid @ModelAttribute("newPrice") NewPriceDTO priceDTO, BindingResult bindingResult, Model model,
                              long id) {
        if(!bindingResult.hasErrors()) {
            priceDTO.setProdId(id);
            if (productService.updatePrice(priceDTO.getPrice(), priceDTO.getProdId())) {
                model.addAttribute("messageUpdPrice", ControllerMessageManager.UPDATE_PRICE_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdPrice", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "product/updatePriceProd";
    }

    @GetMapping("/updRating/{id}")
    public String updateRating(@PathVariable long id, @ModelAttribute("newRating") NewRatingDTO ratingDTO, Model model){
        model.addAttribute("prodId", id);
        return "product/updRatingProd";
    }

    @PostMapping("/updRating")
    public String updateRating(@Valid @ModelAttribute("newRating") NewRatingDTO ratingDTO, BindingResult bindingResult, Model model, HttpSession session,
                               long id){
        if(!bindingResult.hasErrors()) {
            User user = (User) session.getAttribute("user");
            ratingDTO.setProdId(id);
            if (productService.updateScore(ratingDTO.getScore(), ratingDTO.getProdId(), user)) {
                model.addAttribute("messageUpdRating", ControllerMessageManager.UPDATE_RATING_SUCCESSFULLY);
            } else {
                model.addAttribute("messageUpdRating", ControllerMessageManager.OPERATION_FAILED);
            }
        }
        return "product/updRatingProd";
    }

    @PostMapping("/byType")
    public String getByType(CategoryOfProduct category, Model model){
        List<Product> products = productService.getByCategory(category);
        model.addAttribute("listProd", products);
        return "store";
    }

    @GetMapping("/byOwner")
    public String getByOwner(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Product> productList = productService.getByOwner(user);
        List<CardDTO> cardProducts = new ArrayList<>();
        for (Product prod : productList) {
            cardProducts.add(new CardDTO(prod, commentService.getCommentsByIdProduct(prod.getId()), prod.getRating()));
        }
        model.addAttribute("listProd", cardProducts);
        return "user/userProducts";
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
            return "product/product";
        }else return "store";
    }

    @GetMapping("/all")
    public String getAll(Model model){
        Optional<List<Product>> products = Optional.ofNullable(productService.getAll());
        products.ifPresent(productList -> model.addAttribute("listProd", productList));
        return "store";
    }

    @GetMapping("/byProducer/{id}")
    public String getByProducer(Model model, @PathVariable long id){
        List<Product> productList = productService.getByProducer(Producer.builder().id(id).build());
        List<CardDTO> cardProducts = new ArrayList<>();
        for (Product prod : productList) {
            cardProducts.add(new CardDTO(prod, commentService.getCommentsByIdProduct(prod.getId()), prod.getRating()));
        }
        model.addAttribute("producerProducts", cardProducts);
        return "producer/producerProducts";
    }

    @GetMapping("/updProdPage/{id}")
    public String getUpdProductPage(@PathVariable long id, Model model){
        Optional<Product> product = productService.getById(id);
        if(product.isPresent()) {
            CardDTO cardDTO = CardDTO.builder()
                    .product(product.get())
                    .comments(commentService.getCommentsByIdProduct(id))
                    .build();
            model.addAttribute("card", cardDTO);
            return "product/productUpdPage";
        }
        return "store";
    }

    @GetMapping("/setSaleStatus/{status}/{id}")
    public String setForSaleStatus(@PathVariable(name = "status") boolean status, @PathVariable(name = "id") long id){
        productService.setSaleStatus(status, id);
        return "user/userProducts";
    }

    @GetMapping("/allForAdmin")
    public String getAllForAdmin(Model model){
        Optional<List<Product>> products = Optional.ofNullable(productService.getAll());
        products.ifPresent(productList -> model.addAttribute("allProducts", productList));
        return "admin/adminLists";
    }
}
