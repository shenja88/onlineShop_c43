package by.c43.store.dao;

import by.c43.store.entity.Comment;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
@Component
@AllArgsConstructor
public class CommentDaoImpl implements CommentDao {
    private final SessionFactory sessionFactory;



    @Override
    public void save(Comment comment) {
        Session session = sessionFactory.openSession();
        session.save(comment);
        session.close();
    }


    @Override
    public void update(long id, String newDescription) {
        Session session = sessionFactory.openSession();
        Comment comment = session.load(Comment.class, id);
        comment.setDescription(newDescription);
        session.update(comment);
        session.close();
    }


    @Override
    public void delete(long id) {
        Session session = sessionFactory.openSession();
        Comment comment = session.get(Comment.class, id);
        session.delete(comment);
        session.close();
    }

    @Override
    public boolean isExistById(long id) {
        Session session = sessionFactory.openSession();
        Optional<Comment> comment = Optional.ofNullable(session.get(Comment.class, id));
        return comment.isPresent();
    }


    @Override
    public boolean isExistByInfo(Comment comment) {
        Session session = sessionFactory.openSession();
        Optional<Comment> commentOpt = Optional.ofNullable((Comment)session.createQuery(
             " FROM Comments WHERE description = :checkDescription AND User.email = :checkUserEmail ")
                .setParameter("checkDescription", comment.getDescription())
                .setParameter("checkUserEmail", comment.getUser().getEmail())
                .getSingleResult());
        return commentOpt.isPresent();

    }


    @Override
    public Optional<Comment> getById(long id) {
        Session session = sessionFactory.openSession();
        return Optional.ofNullable(session.get(Comment.class, id));
    }


}
