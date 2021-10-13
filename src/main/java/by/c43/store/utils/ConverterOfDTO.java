package by.c43.store.utils;

import by.c43.store.dto.addressDTO.AllArgAddressDTO;
import by.c43.store.dto.cardDTO.CardDTO;
import by.c43.store.dto.producerDTO.AllArgsProducerDTO;
import by.c43.store.dto.producerDTO.EmailPasswordProducerDTO;
import by.c43.store.dto.productDTO.AllArgsProductDTO;
import by.c43.store.dto.telephonesDTO.NumberTelDTO;
import by.c43.store.dto.usersDTO.AllArgUsersDTO;
import by.c43.store.dto.usersDTO.EmailPasswordUsersDTO;
import by.c43.store.entity.*;

import java.util.List;

public class ConverterOfDTO {

    public static User getAllArgUsersDTO(AllArgUsersDTO allArgUsersDTO){
        return User.builder()
                .name(allArgUsersDTO.getName())
                .email(allArgUsersDTO.getEmail())
                .password(allArgUsersDTO.getPassword())
                .picture(allArgUsersDTO.getPicture())
                .telephone(Telephone.builder()
                        .number(allArgUsersDTO.getNumber())
                        .build())
                .build();
    }

    public static User getEmailPasswordUsersDTO(EmailPasswordUsersDTO emailPasswordUsersDTO){
        return User.builder()
                .email(emailPasswordUsersDTO.getEmail())
                .password(emailPasswordUsersDTO.getPassword())
                .build();
    }

    public static Telephone getTelDTO(NumberTelDTO numberTelDTO){
        return Telephone.builder()
                .number(numberTelDTO.getNumber())
                .build();
    }

    public static Product getAllArgsProductDTO(AllArgsProductDTO allArgsProductDTO){
        return Product.builder()
                .name(allArgsProductDTO.getName())
                .description(allArgsProductDTO.getDescription())
                .category(allArgsProductDTO.getCategory())
                .picture(allArgsProductDTO.getPicture())
                .price(allArgsProductDTO.getPrice())
                .build();
    }

    public static Producer getAllArgsProducerDTO(AllArgsProducerDTO allArgsProducerDTO){
        return Producer.builder()
                .name(allArgsProducerDTO.getName())
                .email(allArgsProducerDTO.getEmail())
                .password(allArgsProducerDTO.getPassword())
                .description(allArgsProducerDTO.getDescription())
                .picture(allArgsProducerDTO.getPicture())
                .address(Address.builder()
                        .country(allArgsProducerDTO.getCountry())
                        .city(allArgsProducerDTO.getCity())
                        .street(allArgsProducerDTO.getStreet())
                        .home(allArgsProducerDTO.getHome())
                        .build())
                .telephone(Telephone.builder()
                        .number(allArgsProducerDTO.getNumber())
                        .build())
                .build();
    }

    public static Producer getEmailPasswordProducerDTO(EmailPasswordProducerDTO emailPasswordProducerDTO){
        return Producer.builder()
                .email(emailPasswordProducerDTO.getEmail())
                .password(emailPasswordProducerDTO.getPassword())
                .build();
    }

    public static Address getAllArgAddressDTO(AllArgAddressDTO allArgAddressDTO){
        return Address.builder()
                .country(allArgAddressDTO.getCountry())
                .city(allArgAddressDTO.getCity())
                .street(allArgAddressDTO.getStreet())
                .home(allArgAddressDTO.getHome())
                .build();
    }

    public static CardDTO getCardDto(Product product, List<Comment> comments, Rating rating ){
        return CardDTO.builder()
                .product(product)
                .comments(comments)
                .rating(rating)
                .build();
    }
}
