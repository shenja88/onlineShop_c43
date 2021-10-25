package by.c43.store.dao;

import by.c43.store.entity.Comment;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Component
@AllArgsConstructor
public class CommentDaoImpl implements CommentDao {
    private final SessionFactory sessionFactory;

    private final static String GET_BY_PRODUCT_ID = "FROM Comment where product.id =: id_product";

    @Override
    public void save(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }

    @Override
    public void update(long id, String newDescription) {
        Comment comment = sessionFactory.getCurrentSession().load(Comment.class, id);
        comment.setDescription(newDescription);
        sessionFactory.getCurrentSession().update(comment);
    }


    @Override
    public void delete(long id) {
        Comment comment = sessionFactory.getCurrentSession().get(Comment.class, id);
        sessionFactory.getCurrentSession().delete(comment);
    }

    @Override
    public boolean isExistById(long id) {
        Optional<Comment> comment = Optional.ofNullable(sessionFactory.getCurrentSession().get(Comment.class, id));
        return comment.isPresent();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Comment.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getByIdProduct(long id) {
        return sessionFactory.getCurrentSession().createQuery(GET_BY_PRODUCT_ID, Comment.class)
                .setParameter("id_product", id)
                .getResultList();
    }
}
