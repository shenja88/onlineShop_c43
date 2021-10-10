package by.c43.store.dao;

import by.c43.store.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    void save(Comment comment);

    void update(long id, String newDescription);

    void delete(long id);

    Optional<Comment> getById(long id);

    List<Comment> getByIdProduct(long id);

    boolean isExistById(long id);

    boolean isExistByInfo(Comment comment);


}