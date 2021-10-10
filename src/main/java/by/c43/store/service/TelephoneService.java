package by.c43.store.service;

import by.c43.store.dao.TelephoneDao;
import by.c43.store.entity.Telephone;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TelephoneService {

    TelephoneDao telephoneDao;

    public boolean save (Telephone telephone){
        if (telephoneDao.isExistByNumber(telephone.getNumber())){
            return false;
        }else {
            telephoneDao.save(telephone);
            return true;
        }
    }

    public boolean delete(long id){
        if (telephoneDao.isExistById(id)){
            telephoneDao.delete(id);
            return true;
        }else{
            return false;
        }
    }

    public boolean update(Telephone telephone, String newNumber){
        if (telephoneDao.isExistByNumber(telephone.getNumber())) {
            telephoneDao.update(newNumber, telephone.getId());
            return true;
        }else {
            return false;
        }
    }

    public Optional<Telephone> getById(long id){
        if (telephoneDao.isExistById(id)){
            return Optional.of(telephoneDao.getById(id));
        }
        return Optional.empty();
    }
}
