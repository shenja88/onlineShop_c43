package by.c43.store.service;

import by.c43.store.dao.CommentDao;
import by.c43.store.entity.Comment;
import by.c43.store.entity.Product;
import by.c43.store.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentDao commentDao;

    public boolean saveComment(Comment comment){
        if(commentDao.isExistByInfo(comment)){
            commentDao.save(comment);
            return true;
        }else return false;
    }

    public boolean updateDescription(long id, String newDescription){
        if(commentDao.isExistById(id)){
            commentDao.update(id, newDescription);
            return true;
        }else return false;
    }

    public boolean deleteById(long id){
        if(commentDao.isExistById(id)){
            commentDao.delete(id);
            return true;
        }else return false;
    }

    public List<Comment> getCommentsByIdProduct(long idProduct){
        return commentDao.getByIdProduct(idProduct);
    }
}
