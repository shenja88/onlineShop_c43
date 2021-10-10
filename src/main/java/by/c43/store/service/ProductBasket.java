package by.c43.store.service;

import by.c43.store.dao.ProductDao;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class ProductBasket {
    private final ProductDao productDao;
    
    private final List<Long> basketProduct = new ArrayList<>();

    public ProductBasket(ProductDao productDao) {
        this.productDao = productDao;
    }

    public boolean saveInBasket(Long productId) {
        if (!basketProduct.contains(productId)) {
            basketProduct.add(productId);
            productDao.setReservedStatus(true, productId);
            return true;
        }
        return false;
    }

    public List<Product> getReservedProducts(){
        List<Product> products = new ArrayList<>();
        for (Long l : basketProduct){
            products.add(productDao.getById(l));
        }
        return products;
    }

    public void setStatusOwnerAfterPurchase(User user){
        basketProduct.forEach(x -> productDao.setOwner(user, x));
        resetReservedStatusAfterLogOutOrPurchase();
    }

    public void resetReservedStatusAfterLogOutOrPurchase(){
        basketProduct.forEach(x -> productDao.setReservedStatus(false, x));
        basketProduct.clear();
    }
}
