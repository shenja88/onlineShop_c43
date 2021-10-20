package by.c43.store.aspect;


import by.c43.store.dto.addressDTO.ArgNoIdAddressDTO;
import by.c43.store.dto.producerDTO.*;
import by.c43.store.dto.telephonesDTO.NumberTelDTO;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProducerLogAspect {
    private final Logger logger = LoggerFactory.getLogger(ProducerLogAspect.class.getSimpleName());

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.registration(..)) && args(allArgsProducerDTO, *, ..)")
    public void reg(AllArgsProducerDTO allArgsProducerDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.authorization(..)) && args(producerDTO, *, ..)")
    public void auth(EmailPasswordProducerDTO producerDTO){

    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.updateName(..)) && args(producerNameDTO, *, ..)")
    public void updName(ProducerNameDTO producerNameDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.updateEmail(..)) && args(producerEmailDTO, *, ..)")
    public void updEmail(ProducerEmailDTO producerEmailDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.updatePassword(..)) && args(passwordDTO, *, ..)")
    public void updPassword(ProducerPasswordDTO passwordDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.updatePicture(..)) && args(pictureDTO, *, ..)")
    public void updPicture(ProducerPictureDTO pictureDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.updateDescription(..)) && args(descriptionDTO, *, ..)")
    public void updDescription(ProducerDescriptionDTO descriptionDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.updateAddress(..)) && args(addressDTO, *, ..)")
    public void updAddress(ArgNoIdAddressDTO addressDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.addTelephone(..)) && args(numberTelDTO, *, ..)")
    public void saveTelephone(NumberTelDTO numberTelDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.updateTelephone(..)) && args(numberTelDTO, *, ..)")
    public void updTelephone(NumberTelDTO numberTelDTO){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.delete(..)) && args(id, ..)")
    public void remove(long id){
    }

    @Pointcut("execution(public * by.c43.store.controller.ProducerController.logOut(..))")
    public void logOut(){
    }

    @After(value = "reg(allArgsProducerDTO)", argNames = "allArgsProducerDTO")
    public void registration(AllArgsProducerDTO allArgsProducerDTO){
        logger.info("Register new producer with name - {}.", allArgsProducerDTO.getName());
    }

    @After(value = "auth(producerDTO)", argNames = "producerDTO")
    public void authorization(EmailPasswordProducerDTO producerDTO){
        logger.info("Authorization for the user with the email - {}, is done.", producerDTO.getEmail());
    }

    @After(value = "updName(producerNameDTO)", argNames = "producerNameDTO")
    public void updateName(ProducerNameDTO producerNameDTO){
        logger.info("Producer's name changed to - {}.", producerNameDTO.getName());
    }

    @After(value = "updEmail(producerEmailDTO)", argNames = "producerEmailDTO")
    public void updateEmail(ProducerEmailDTO producerEmailDTO){
        logger.info("Producer's email changed to - {}.", producerEmailDTO.getEmail());
    }

    @After(value = "updPassword(passwordDTO)", argNames = "passwordDTO")
    public void updatePassword(ProducerPasswordDTO passwordDTO){
        logger.info("Producer's password changed.");
    }

    @After(value = "updPicture(pictureDTO)", argNames = "pictureDTO")
    public void updatePicture(ProducerPictureDTO pictureDTO){
        logger.info("Producer's picture changed.");
    }

    @After(value = "updDescription(descriptionDTO)", argNames = "descriptionDTO")
    public void updateDescription(ProducerDescriptionDTO descriptionDTO){
        logger.info("Producer's description changed.");
    }

    @After(value = "updAddress(addressDTO)", argNames = "addressDTO")
    public void updateAddress(ArgNoIdAddressDTO addressDTO){
        logger.info("Producer's address changed, new address - {}", addressDTO.toString());
    }

    @After(value = "saveTelephone(numberTelDTO)", argNames = "numberTelDTO")
    public void addNewTel(NumberTelDTO numberTelDTO){
        logger.info("Producer add new telephone - {}.", numberTelDTO.getNumber());
    }

    @After(value = "updTelephone(numberTelDTO)", argNames = "numberTelDTO")
    public void updateTel(NumberTelDTO numberTelDTO){
        logger.info("Producer's telephone changed - {}", numberTelDTO.getNumber());
    }

    @After(value = "remove(id)", argNames = "id")
    public void deleteById(long id){
        logger.info("Producer with id - {} has been deleted.", id);
    }

    @After(value = "logOut()")
    public void signOut(){
        logger.info("Producer is out of session.");
    }



}
