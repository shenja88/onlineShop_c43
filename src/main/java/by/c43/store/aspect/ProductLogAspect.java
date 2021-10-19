package by.c43.store.aspect;

import by.c43.store.dto.productDTO.*;
import by.c43.store.entity.CategoryOfProduct;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductLogAspect {
    private final Logger logger = LoggerFactory.getLogger(ProductLogAspect.class.getSimpleName());

    @Pointcut("execution(public * by.c43.store.controller.ProductController.addProduct(..)) && args(dto, *, ..)")
    public void saveProd(AllArgsProductDTO dto){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.delete(..)) && args(id, ..)")
    public void deleteProd(long id){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.updateName(..)) && args(dto, *, ..)")
    public void updName(NameProductDTO dto){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.updateDescription(..)) && args(dto, *, ..)")
    public void updDescription(DescriptionProductDTO dto){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.updatePicture(..)) && args(dto, *, ..)")
    public void updPicture(PictureProductDTO dto){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.updateType(..)) && args(dto, *, ..)")
    public void updType(TypeProductDTO dto){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.updatePrice(..)) && args(priceDTO, *, ..)")
    public void updPrice(NewPriceDTO priceDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.updateRating(..)) && args(ratingDTO, *, ..)")
    public void updRating(NewRatingDTO ratingDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.getByType(..)) && args(category, ..)")
    public void getByType(CategoryOfProduct category){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.getByOwner(..)))")
    public void getByOwner(){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.getByProducer(..)))")
    public void getByProducer(){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.getByPrice(..))")
    public void getByPrice(){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProductController.getByRating(..))")
    public void getByRating(){
    }

    @After(value = "saveProd(dto)", argNames = "dto")
    public void addNewProd(AllArgsProductDTO dto){
        logger.info("Add new product with name - {}.", dto.getName());
    }

    @After(value = "deleteProd(id)", argNames = "id")
    public void removeProd(long id){
        logger.info("Remove product with id - {}.", id);
    }

    @After(value = "updName(dto)", argNames = "dto")
    public void updateName(NameProductDTO dto){
        logger.info("Product with id ({}) has changed its name to {}.", dto.getId(), dto.getName());
    }

    @After(value = "updDescription(dto)", argNames = "dto")
    public void updateDescription(DescriptionProductDTO dto){
        logger.info("Product with id ({}) has changed its description.", dto.getId());
    }

    @After(value = "updPicture(dto)", argNames = "dto")
    public void updatePicture(PictureProductDTO dto){
        logger.info("Product with id ({}) has changed its picture.", dto.getId());
    }

    @After(value = "updType(dto)", argNames = "dto")
    public void updateType(TypeProductDTO dto){
        logger.info("Product with id ({}) has changed its type to {}.", dto.getId(), dto.getCategory());
    }

    @After(value = "updPrice(priceDTO)", argNames = "priceDTO")
    public void updatePrice(NewPriceDTO priceDTO){
        logger.info("Product with id ({}) has changed its price to {}.", priceDTO.getProdId(), priceDTO.getPrice());
    }

    @After(value = "updRating(ratingDTO)", argNames = "ratingDTO")
    public void updateRating(NewRatingDTO ratingDTO){
        logger.info("Product with id ({}) has changed its rating.", ratingDTO.getProdId());
    }

    @After(value = "getByType(category)", argNames = "category")
    public void getAllByType(CategoryOfProduct category){
        logger.info("Request to products with category - {}.", category);
    }

    @After(value = "getByOwner()")
    public void getAllByOwner(){
        logger.info("Request to products by owner.");
    }

    @After(value = "getByProducer()")
    public void getAllByProducer(){
        logger.info("Request to products by producer.");
    }

    @After(value = "getByPrice()")
    public void getAllByPrice(){
        logger.info("Request to products by price.");
    }

    @After(value = "getByRating()")
    public void getAllByRating(){
        logger.info("Request to products by rating.");
    }
}
