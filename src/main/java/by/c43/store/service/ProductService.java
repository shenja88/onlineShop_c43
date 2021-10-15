package by.c43.store.service;

import by.c43.store.dao.ProductDao;
import by.c43.store.dao.RatingDao;
import by.c43.store.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final RatingDao ratingDao;

    public boolean save(Product product) {
        if (productDao.isExistByInfo(product)) {
            productDao.save(product);
            return true;
        }
        return false;
    }

    public boolean delete(User user, Product product) {
        if (productDao.isExistById(product.getId())) {
            if (user.getTypeOfUser().equals(TypeOfUser.ADMIN) || user.equals(product.getOwner())) {
                productDao.deleteProduct(product.getId());
                return true;
            }
        }
        return false;
    }

    public boolean updateName(String newName, long productId) {
        Product product = productDao.getById(productId);
        if (!product.getName().equals(newName)) {
            productDao.updateName(newName, productId);
            return true;
        }
        return false;
    }

    public boolean updateEmail(String newDescription, long productId) {
        Product product = productDao.getById(productId);
        if (!product.getDescription().equals(newDescription)) {
            productDao.updateDescription(newDescription, productId);
            return true;
        }
        return false;
    }

    public boolean updatePicture(String newPicture, long productId) {
        Product product = productDao.getById(productId);
        if (!product.getPicture().equals(newPicture)) {
            productDao.updatePicture(newPicture, productId);
            return true;
        }
        return false;
    }

    public boolean updateTypeProduct(CategoryOfProduct newCategory, long productId) {
        Product product = productDao.getById(productId);
        if (!product.getCategory().equals(newCategory)) {
            productDao.updateProductCategory(newCategory, productId);
            return true;
        }
        return false;
    }

    public boolean updatePrice(double newPrice, long productId) {
        Product product = productDao.getById(productId);
        if (product.getPrice() != newPrice) {
            productDao.updatePrice(newPrice, productId);
            return true;
        }
        return false;
    }

    public boolean updateScore(double newScore, long productId, User user) {
        Product product = productDao.getById(productId);
        if(product.getRating().getUsers().contains(user.getId())){
           return false;
        } else {
            double newRating = (product.getRating().getScore() + newScore) / 2;
            ratingDao.setNewScore(newRating, product.getRating().getId(), user.getId());
        }
        return true;
    }

    public Product getById(long id) {
        return productDao.getById(id);
    }

    public List<Product> getAllByPriceLowUp(int low, int up) {
        return productDao.getAllByPrice(low, up);
    }

    public List<Product> getAllByPriceLow(int low) {
        return productDao.getAllByPriceLowerLimit(low);
    }

    public List<Product> getAllByPriceUp(int up) {
        return productDao.getAllByPriceUpLimit(up);
    }

    public List<Product> getAllByRatingLowUp(int low, int up) {
        return productDao.getAllByScore(low, up);
    }

    public List<Product> getAllByRatingLow(int low) {
        return productDao.getAllByScoreLowerLimit(low);
    }

    public List<Product> getAllByRatingUp(int up) {
        return productDao.getAllByScoreUpLimit(up);
    }

    public List<Product> getByCategory(CategoryOfProduct category) {
        return productDao.getByCategory(category);
    }

    public List<Product> getByOwner(User user) {
        return productDao.getAllByOwner(user.getId());
    }

    public List<Product> getByProducer(Producer producer) {
        return productDao.getAllByProducer(producer.getId());
    }
}
