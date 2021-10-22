package by.c43.store.service;


import by.c43.store.dao.ProducerDao;
import by.c43.store.dao.TelephoneDao;
import by.c43.store.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProducerService {

    private final ProducerDao producerDao;
    private final TelephoneDao telephoneDao;

    public boolean saveProducer(Producer producer) {
        if (!producerDao.isExistByEmail(producer.getEmail())) {
            producerDao.addProducer(producer);
            return true;
        } else return false;
    }

    public boolean deleteProducer(long id, User user) {
        if (producerDao.isExistById(id) && user.getTypeOfUser() == TypeOfUser.ADMIN ) {
            producerDao.deleteProducer(id);
            return true;
        } else return false;
    }

    public boolean updateName(Producer producer, String newName) {
        if (!producer.getName().equals(newName)) {
            producerDao.updateName(newName, producer.getId());
            return true;
        } else return false;
    }

    public boolean updateEmail(Producer producer, String newEmail) {
        if (!producer.getEmail().equals(newEmail)) {
            producerDao.updateEmail(newEmail, producer.getId());
            return true;
        } else return false;
    }

    public boolean updatePassword(Producer producer, String newPassword, String oldPassword) {
        if (producer.getPassword().equals(oldPassword)) {
            if (!oldPassword.equals(newPassword)) {
                producerDao.updatePassword(newPassword, producer.getId());
                return true;
            }
        }
        return false;
    }

    public boolean updateDescription(Producer producer, String newDescription) {
        if (!producer.getDescription().equals(newDescription)) {
            producerDao.updateDescription(newDescription, producer.getId());
            return true;
        } else return false;
    }

    public boolean updatePicture(Producer producer, String newPicture) {
        if (!producer.getPicture().equals(newPicture)) {
            producerDao.updatePicture(newPicture, producer.getId());
            return true;
        } else return false;
    }

    public boolean updateAddress(Producer producer, Address address) {
        if (!equalsFields(producer, address)) {
            producerDao.updateAddress(address, producer.getId());
            return true;
        }
        return false;
    }

    public boolean updateTelephone(Producer producer, Telephone telephone) {
        if (equalsByNumber(producer, telephone)) {
            return false;
        } else {
            producerDao.updateTelephone(producer.getId(), telephone);
        }
        return true;
    }

    public boolean addTelephone(Producer producer, Telephone telephone){
        if(equalsByNumber(producer, telephone)){
            return false;
        } else {
            producerDao.addTelephone(producer.getId(), telephone);
        }
        return true;
    }

    public Optional<Producer> getProducerById(long id) {
        return Optional.of(producerDao.getById(id));
    }

    public Optional<Producer> getProducerByEmail(String email){
        return Optional.of(producerDao.getByEmail(email));
    }

    private boolean equalsFields(Producer producer, Address address) {
        return (producer.getAddress().getCountry().equals(address.getCountry())
                && producer.getAddress().getCity().equals(address.getCity())
                && producer.getAddress().getStreet().equals(address.getStreet())
                && producer.getAddress().getHome().equals(address.getHome()));
    }

    private boolean equalsByNumber(Producer producer, Telephone telephone) {
        return producer.getTelephones().stream().anyMatch(t -> t.getNumber().equals(telephone.getNumber()));
    }

    public boolean deleteTelephone(Producer producer, long id){
        if(producer.getTelephones().size() > 1){
            Telephone telephone = telephoneDao.getById(id);
            producerDao.deleteTelephone(telephone, producer.getId());
            return true;
        }else return false;
    }
}
