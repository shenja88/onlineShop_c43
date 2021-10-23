package by.c43.store.dao;

import by.c43.store.entity.CategoryOfProduct;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ProductDaoImpl implements ProductDao {
    private final SessionFactory sessionFactory;

    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static final String GET_BY_ID = "from Product where id =: id";
    private static final String GET_BY_OWNER_ID = "from Product where owner.id =: ownerId";
    private static final String GET_BY_PRODUCER_ID = "from Product where producer.id =: producerId";
    private static final String GET_BY_PRICE_LOW_UP = "from Product where price >=: low and price <=: up";
    private static final String GET_BY_PRICE_LOW = "from Product where price >=: low";
    private static final String GET_BY_PRICE_UP = "from Product where price <=: up";
    private static final String GET_BY_RATING_LOW_UP = "from Product where rating.score >=: low and rating.score <=: up";
    private static final String GET_BY_RATING_LOW = "from Product where rating.score >=: low";
    private static final String GET_BY_RATING_UP = "from Product where rating.score <=: up";
    private static final String GET_BY_INFO = "from Product where name =: name and category =: category and Producer.id =: prodId";
    private static final String GET_BY_CATEGORY = "from Product where category =: category";
    private static final String GET_BY_SALE_STATUS = "from Product where saleStatus =: status";
    private static final String GET_ALL = "from Product";

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        sessionFactory.getCurrentSession().delete(product);
    }

    @Override
    public void updateName(String newName, long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        product.setName(newName);
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void updateDescription(String newDescription, long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        product.setDescription(newDescription);
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void updatePrice(double newPrice, long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        product.setPrice(newPrice);
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void updateProductCategory(CategoryOfProduct newProductCategory, long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        product.setCategory(newProductCategory);
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void updatePicture(String newPicture, long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        product.setPicture(newPicture);
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void setOwner(User user, long productId) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, productId);
        product.setOwner(user);
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void setReservedStatus(boolean status, long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        product.setReservedStatus(status);
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void setForSaleStatus(boolean status, long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        product.setSaleStatus(status);
        sessionFactory.getCurrentSession().update(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getById(long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByOwner(long ownerId) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_OWNER_ID, Product.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByProducer(long producerId) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_PRODUCER_ID, Product.class)
                .setParameter("producerId", producerId)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByPrice(int lowerLimit, int upperLimit) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_PRICE_LOW_UP, Product.class)
                .setParameter("low", lowerLimit)
                .setParameter("up", upperLimit)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByPriceLowerLimit(int lowerLimit) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_PRICE_LOW, Product.class)
                .setParameter("low", lowerLimit)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByPriceUpLimit(int upperLimit) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_PRICE_UP, Product.class)
                .setParameter("up", upperLimit)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByScoreLowerLimit(int lowerLimit) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_RATING_LOW, Product.class)
                .setParameter("low", lowerLimit)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByScore(int lowerLimit, int upperLimit) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_RATING_LOW_UP, Product.class)
                .setParameter("low", lowerLimit)
                .setParameter("up", upperLimit)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByScoreUpLimit(int upperLimit) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_RATING_UP, Product.class)
                .setParameter("up", upperLimit)
                .getResultList();
    }

    @Override
    public List<Product> getSaleStatus(boolean status) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_SALE_STATUS, Product.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getByCategory(CategoryOfProduct category) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_CATEGORY, Product.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<Product> getAll() {
        return sessionFactory.getCurrentSession().createQuery(GET_ALL, Product.class)
                .getResultList();
    }

    @Override
    public boolean isExistById(long id) {
        Optional<Product> optionalProduct = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_ID, Product.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        return optionalProduct.isPresent();
    }

    @Override
    public boolean isExistByInfo(Product product) {
        Optional<Product> optionalProduct = sessionFactory.getCurrentSession()
                .createQuery(GET_BY_INFO, Product.class)
                .setParameter("name", product.getName())
                .setParameter("category", product.getCategory())
                .setParameter("prodId", product.getProducer().getId())
                .uniqueResultOptional();
        return optionalProduct.isPresent();
    }
}
