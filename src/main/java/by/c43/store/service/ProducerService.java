package by.c43.store.service;


import by.c43.store.dao.ProducerDao;
import by.c43.store.entity.Producer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProducerService {

    private final ProducerDao producerDao;

    public boolean saveProducer(Producer producer){
        if(!producerDao.isExistByEmail(producer.getEmail())){
            producerDao.addProducer(producer);
            return true;
        }else return false;
    }

    public boolean deleteProducer(Producer producer){
        if(producerDao.isExistById(producer.getId())){
            producerDao.deleteProducer(producer.getId());
            return true;
        }else return false;
    }

    public boolean updateName(Producer producer, String newName){
        if(!producer.getName().equals(newName)){
            producerDao.updateName(newName, producer.getId());
            return true;
        }else return false;
    }

    public boolean updateEmail(Producer producer, String newEmail){
        if(!producer.getEmail().equals(newEmail)){
            producerDao.updateEmail(newEmail, producer.getId());
            return true;
        }else return false;
    }

    public boolean updatePassword(Producer producer, String newPassword, String oldPassword){
        if(producer.getPassword().equals(oldPassword)){
            if(!oldPassword.equals(newPassword)){
                producerDao.updatePassword(newPassword, producer.getId());
                return true;
            }
        }
        return false;
    }

    public boolean updateDescription(Producer producer, String newDescription){
        if(!producer.getDescription().equals(newDescription)){
            producerDao.updateDescription(newDescription, producer.getId());
            return true;
        }else return false;
    }

    public boolean updatePicture(Producer producer, String newPicture){
        if(!producer.getPicture().equals(newPicture)){
            producerDao.updatePicture(newPicture, producer.getId());
            return true;
        }else return false;
    }

    public Optional<Producer> getProducerById(long id){
        return Optional.of(producerDao.getById(id));
    }

}
