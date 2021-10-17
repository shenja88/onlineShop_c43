package by.c43.store.aspect;

import by.c43.store.dto.commentDTO.DescriptionProductUserDTO;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class CommentLogAspect {
    private final Logger logger = LoggerFactory.getLogger(UserLogAspect.class);

    @Pointcut("execution(public * by.c43.store.controller.CommentController.addComment(..)) && args(commentDTO, *, ..)")
    public void newComment(DescriptionProductUserDTO commentDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.CommentController.updateComments(..)) && args(id, ..)")
    public void updComment(long id){
    }

    @Pointcut("execution(public * by.c43.store.controller.CommentController.deleteComment(..)) && args(id, ..)")
    public void removeComment(long id){
    }

    @Pointcut("execution(public * by.c43.store.controller.CommentController.showAllCommentsByProductId(..)) && args(id, ..))")
    public void getAllCommentByProductId(long id){
    }

    @After(value = "newComment(commentDTO),", argNames = "commentDTO")
    public void newCom(DescriptionProductUserDTO commentDTO){
    logger.info("New comment added.");
    }

    @After(value = "updComment(id),", argNames = "id")
    public  void updCom(long id){
        logger.info("Comment with id - {} has been update.", id);
    }

    @After(value = "removeComment(id)", argNames = "id")
    public void removeCom(long id){
        logger.info("Comment with id - {} has been remove.", id);
    }

    @After(value = "getAllCommentByProductId(id)", argNames = "id")
    public void requestAllComByProd(long id){
        logger.info("Request all comments for product with id - {}", id);
    }
}
