package by.c43.store.controller;

import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import by.c43.store.service.ProductBasket;
import by.c43.store.utils.ControllerMessageManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/basket")
@Controller
@AllArgsConstructor
public class BasketController {

    @GetMapping("/add/{id}")
    public String addInBasket(@PathVariable long id, HttpSession session) {
        ProductBasket basket = (ProductBasket) session.getAttribute("basket");
        basket.saveInBasket(id);
        return "store";
    }

    @GetMapping("/getProducts")
    public String getReservedProducts(Model model, HttpSession session) {
        ProductBasket basket = (ProductBasket) session.getAttribute("basket");
        List<Product> productList = basket.getReservedProducts();
        model.addAttribute("basketProducts", productList);
        return "user/basket";
    }

    @GetMapping("/buyProd")
    public String buyProducts(Model model, HttpSession session) {
        ProductBasket basket = (ProductBasket) session.getAttribute("basket");
        User user = (User) session.getAttribute("user");
        basket.setStatusOwnerAfterPurchase(user);
        model.addAttribute("purchaseMessage", ControllerMessageManager.BUY_ALL_RESERVED_PRODUCTS_SUCCESSFULLY);
        return "user/basket";
    }
}
