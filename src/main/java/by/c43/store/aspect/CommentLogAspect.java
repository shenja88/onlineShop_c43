package by.c43.store.aspect;

import by.c43.store.dto.commentDTO.DescriptionIdCommentDTO;
import by.c43.store.dto.commentDTO.DescriptionProductDTO;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommentLogAspect {
    private final Logger logger = LoggerFactory.getLogger(CommentLogAspect.class.getSimpleName());

    @Pointcut("execution(public * by.c43.store.controller.CommentController.addComment(..)) && args(commentDTO, *, ..)")
    public void newComment(DescriptionProductDTO commentDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.CommentController.updateComments(..)) && args(commentDTO, ..)")
    public void updComment(DescriptionIdCommentDTO commentDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.CommentController.deleteComment(..)) && args(id, ..)")
    public void removeComment(long id){
    }

    @Pointcut("execution(public * by.c43.store.controller.CommentController.showAllCommentsByProductId(..)) && args(id, ..))")
    public void getAllCommentByProductId(long id){
    }

    @After(value = "newComment(commentDTO)", argNames = "commentDTO")
    public void newCom(DescriptionProductDTO commentDTO){
    logger.info("New comment added.");
    }

    @After(value = "updComment(commentDTO)", argNames = "commentDTO")
    public  void updCom(DescriptionIdCommentDTO commentDTO){
        logger.info("Comment with id - {} has been update.", commentDTO.getCommentId());
    }

    @After(value = "removeComment(id)", argNames = "id")
    public void removeCom(long id){
        logger.info("Comment with id - {} has been remove.", id);
    }

    @After(value = "getAllCommentByProductId(id)", argNames = "id")
    public void requestAllComByProd(long id){
        logger.info("Request all comments for product with id - {}.", id);
    }
}
