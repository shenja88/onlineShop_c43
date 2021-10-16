package by.c43.store.controller;

import by.c43.store.dto.productDTO.*;
import by.c43.store.entity.CategoryOfProduct;
import by.c43.store.entity.Producer;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import by.c43.store.service.ProductService;
import by.c43.store.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public String addProduct(@ModelAttribute("product") AllArgsProductDTO dto, BindingResult bindingResult,
                             HttpSession session, Model model) {
        if (!bindingResult.hasErrors()) {
            Producer producer = (Producer) session.getAttribute("producer");
            Product product = ConverterOfDTO.getAllArgsProductDTO(dto, producer);
            if (productService.save(product)) {
                model.addAttribute("messageAddProd", "Product successfully added!");
            } else {
                model.addAttribute("messageAddProd", "Product addition error");
            }
        }
        return "addProduct";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Product product = productService.getById(id);
        if (productService.delete(user, product)) {
            model.addAttribute("messageRemoveProd", "Product deleted successfully!");
        } else {
            model.addAttribute("messageRemoveProd", "Product removal error!");
        }
        return "store";
    }

    @GetMapping("/udpName/{id}")
    public String updateName(@ModelAttribute("nameDTO") NameProductDTO dto, @PathVariable long id) {
        return "updateNameProd";
    }

    @PostMapping("/updName/{id}")
    public String updateName(@ModelAttribute("nameDTO") NameProductDTO dto, BindingResult bindingResult,
                             Model model, @PathVariable long id) {
        if (!bindingResult.hasErrors()) {
            if (productService.updateName(dto.getName(), id)) {
                model.addAttribute("messageUpdName", "Product name successfully changed!");
            } else {
                model.addAttribute("messageUpdName", "Operation failed!");
            }
        }
        return "updNameProd";
    }

    @GetMapping("/udpDesc/{id}")
    public String updateDescription(@ModelAttribute("descDTO") DescriptionProductDTO dto, @PathVariable long id) {
        return "updateDescriptionProd";
    }

    @PostMapping("/updName/{id}")
    public String updateDescription(@ModelAttribute("descDTO") DescriptionProductDTO dto, BindingResult bindingResult,
                                    Model model, @PathVariable long id) {
        if (!bindingResult.hasErrors()) {
            if (productService.updateDescription(dto.getDescription(), id)) {
                model.addAttribute("messageUpdDescription", "Product description successfully changed!");
            } else {
                model.addAttribute("messageUpdDescription", "Operation failed!");
            }
        }
        return "updDescriptionProd";
    }

    @GetMapping("/udpPicture/{id}")
    public String updatePicture(@ModelAttribute("pictureDTO") PictureProductDTO dto, @PathVariable long id) {
        return "updatePictureProd";
    }

    @PostMapping("/updPicture/{id}")
    public String updatePicture(@ModelAttribute("pictureDTO") PictureProductDTO dto, BindingResult bindingResult,
                                Model model, @PathVariable long id) {
        if (!bindingResult.hasErrors()) {
            if (productService.updatePicture(dto.getPicture(), id)) {
                model.addAttribute("messageUpdPicture", "Product picture successfully changed!");
            } else {
                model.addAttribute("messageUpdPicture", "Operation failed!");
            }
        }
        return "updPictureProd";
    }

    @GetMapping("/udpType/{id}")
    public String updateType(@ModelAttribute("typeDTO") TypeProductDTO dto, @PathVariable long id) {
        return "updateTypeProd";
    }

    @PostMapping("/updType/{id}")
    public String updateType(@ModelAttribute("typeDTO") TypeProductDTO dto, BindingResult bindingResult,
                             Model model, @PathVariable long id) {
        if (!bindingResult.hasErrors()) {
            if (productService.updateTypeProduct(dto.getCategory(), id)) {
                model.addAttribute("messageUpdType", "Product type successfully changed!");
            } else {
                model.addAttribute("messageUpdType", "Operation failed!");
            }
        }
        return "updTypeProd";
    }

    @GetMapping("/udpPrice/{id}")
    public String updatePrice(@PathVariable long id) {
        return "updatePriceProd";
    }

    @PostMapping("/updPrice/{id}")
    public String updatePrice(double price, Model model, @PathVariable long id) {
        if (productService.updatePrice(price, id)) {
            model.addAttribute("messageUpdPicture", "Product type successfully changed!");
        } else {
            model.addAttribute("messageUpdPicture", "Operation failed!");
        }
        return "updPriceProd";
    }

    @GetMapping("/updRating/{id}")
    public String updateRating(@PathVariable String id){
        return "updRatingProd";
    }

    @PostMapping("/updRating/{id}")
    public String updateRating(@PathVariable long id, double newPrice, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(productService.updateScore(newPrice, id, user)){
            model.addAttribute("messageUpdRating", "Rating successfully changed!");
        } else{
            model.addAttribute("messageUpdRating", "Operation failed!");
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
        if(low != null && up != null){
            int l = Integer.parseInt(low);
            int u = Integer.parseInt(up);
            products = productService.getAllByPriceLowUp(l, u);
        } else if(low == null && up != null){
            int u = Integer.parseInt(up);
            products = productService.getAllByPriceUp(u);
        } else if(low != null) {
            int l = Integer.parseInt(low);
            products = productService.getAllByPriceLow(l);
        }
        model.addAttribute("listProd", products);
        return "store";
    }

    @PostMapping("/byPrice")
    public String getByPrice(String low, String up, Model model){
        List<Product> products = new ArrayList<>();
        if(low != null && up != null){
            int l = Integer.parseInt(low);
            int u = Integer.parseInt(up);
            products = productService.getAllByRatingLowUp(l, u);
        } else if(low == null && up != null){
            int u = Integer.parseInt(up);
            products = productService.getAllByRatingUp(u);
        } else if(low != null) {
            int l = Integer.parseInt(low);
            products = productService.getAllByRatingLow(l);
        }
        model.addAttribute("listProd", products);
        return "store";
    }
}
