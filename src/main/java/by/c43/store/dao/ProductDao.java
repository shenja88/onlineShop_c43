package by.c43.store.dao;

import by.c43.store.entity.CategoryOfProduct;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;

import java.util.List;

public interface ProductDao {

    void save(Product product);

    void deleteProduct(long id);

    void updateName(String newName, long id);

    void updateDescription(String newDescription, long id);

    void updatePrice(double newPrice, long id);

    void updateProductCategory(CategoryOfProduct newProductCategory, long id);

    void updatePicture(String newPicture, long id);

    void setOwner(User user, long productId);

    void setReservedStatus(boolean status, long id);

    List<Product> getAllByScoreLowerLimit(int lowerLimit);

    List<Product> getAllByScoreUpLimit(int upperLimit);

    List<Product> getAllByScore(int lowerLimit, int upperLimit);

    List<Product> getSaleStatus(boolean status);

    void setForSaleStatus(boolean status, long id);

    Product getById(long id);

    List<Product> getAllByOwner(long ownerId);

    List<Product> getAllByProducer(long producerId);

    List<Product> getAllByPrice(int lowerLimit, int upperLimit);

    List<Product> getAllByPriceLowerLimit(int lowerLimit);

    List<Product> getAllByPriceUpLimit(int upperLimit);

    List<Product> getByCategory(CategoryOfProduct category);

    List<Product> getAll();

    boolean isExistById(long id);

}
