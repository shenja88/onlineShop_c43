package by.c43.store.service;

import by.c43.store.dao.AddressDao;
import by.c43.store.entity.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressDao addressDao;

    public boolean delete(long id){
        if(addressDao.isExistById(id)){
            addressDao.delete(id);
            return true;
        }
        return false;
    }

    public Optional<Address> getAddressById(long id){
        return Optional.ofNullable(addressDao.getById(id));
    }


}
