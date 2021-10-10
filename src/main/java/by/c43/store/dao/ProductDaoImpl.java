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


    @Override
    public void save(Product product) {
        Session session = sessionFactory.openSession();
        session.save(product);
        session.close();
    }

    @Override
    public void deleteProduct(long id) {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, id);
        session.delete(product);
        session.close();
    }

    @Override
    public void updateName(String newName, long id) {
        Session session = sessionFactory.openSession();
        Product product = getById(id);
        product.setName(newName);
        session.update(product);
        session.close();
    }

    @Override
    public void updateDescription(String newDescription, long id) {
        Session session = sessionFactory.openSession();
        Product product = getById(id);
        product.setDescription(newDescription);
        session.update(product);
        session.close();
    }

    @Override
    public void updatePrice(double newPrice, long id) {
        Session session = sessionFactory.openSession();
        Product product = getById(id);
        product.setPrice(newPrice);
        session.update(product);
        session.close();
    }

    @Override
    public void updateProductCategory(CategoryOfProduct newProductCategory, long id) {
        Session session = sessionFactory.openSession();
        Product product = getById(id);
        product.setCategory(newProductCategory);
        session.update(product);
        session.close();
    }

    @Override
    public void updatePicture(String newPicture, long id) {
        Session session = sessionFactory.openSession();
        Product product = getById(id);
        product.setPicture(newPicture);
        session.update(product);
        session.close();
    }

    @Override
    public void setOwner(User user, long productId) {
        Session session = sessionFactory.openSession();
        Product product = getById(productId);
        product.setOwner(user);
        session.update(product);
        session.close();
    }

    @Override
    public void setReservedStatus(boolean status, long id) {
        Session session = sessionFactory.openSession();
        Product product = getById(id);
        product.setReservedStatus(status);
        session.update(product);
        session.close();
    }

    @Override
    public void setForSaleStatus(boolean status, long id) {
        Session session = sessionFactory.openSession();
        Product product = getById(id);
        product.setSaleStatus(status);
        session.update(product);
        session.close();
    }

    @Transactional(readOnly = true)
    @Override
    public Product getById(long id) {
        Session session = sessionFactory.openSession();
        Product product = session
                .createQuery(GET_BY_ID, Product.class)
                .setParameter("id", id)
                .getSingleResult();
        session.close();
        return product;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByOwner(long ownerId) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery(GET_BY_OWNER_ID, Product.class)
                .setParameter("ownerId", ownerId)
                .getResultList();
        session.close();
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByProducer(long producerId) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery(GET_BY_PRODUCER_ID, Product.class)
                .setParameter("producerId", producerId)
                .getResultList();
        session.close();
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByPrice(int lowerLimit, int upperLimit) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery(GET_BY_PRICE_LOW_UP, Product.class)
                .setParameter("low", lowerLimit)
                .setParameter("up", upperLimit)
                .getResultList();
        session.close();
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByPriceLowerLimit(int lowerLimit) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery(GET_BY_PRICE_LOW, Product.class)
                .setParameter("low", lowerLimit)
                .getResultList();
        session.close();
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByPriceUpLimit(int upperLimit) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery(GET_BY_PRICE_UP, Product.class)
                .setParameter("up", upperLimit)
                .getResultList();
        session.close();
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByScore(int lowerLimit, int upperLimit) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery(GET_BY_RATING_LOW_UP, Product.class)
                .setParameter("low", lowerLimit)
                .setParameter("up", upperLimit)
                .getResultList();
        session.close();
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByScoreDownLimit(int lowerLimit) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery(GET_BY_RATING_LOW, Product.class)
                .setParameter("low", lowerLimit)
                .getResultList();
        session.close();
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllByScoreUpLimit(int upperLimit) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery(GET_BY_RATING_UP, Product.class)
                .setParameter("up", upperLimit)
                .getResultList();
        session.close();
        return products;
    }

    @Override
    public boolean isExistById(long id) {
        Session session = sessionFactory.openSession();
        Optional<Product> optionalProduct = session
                .createQuery(GET_BY_ID, Product.class)
                .setParameter("id", id)
                .uniqueResultOptional();
        session.close();
        return optionalProduct.isPresent();
    }

    @Override
    public boolean isExistByInfo(Product product) {
        Session session = sessionFactory.openSession();
        Optional<Product> optionalProduct = session
                .createQuery(GET_BY_INFO, Product.class)
                .setParameter("name", product.getName())
                .setParameter("category", product.getCategory())
                .setParameter("prodId", product.getProducer().getId())
                .uniqueResultOptional();
        session.close();
        return optionalProduct.isPresent();
    }
}
