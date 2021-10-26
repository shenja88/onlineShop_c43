package by.c43.store.utils;

import by.c43.store.dto.addressDTO.AllArgsAddressDTO;
import by.c43.store.dto.addressDTO.ArgNoIdAddressDTO;
import by.c43.store.dto.cardDTO.CardDTO;
import by.c43.store.dto.cardDTO.ProducerCardDTO;
import by.c43.store.dto.cardDTO.UserCardInfoDTO;
import by.c43.store.dto.cardDTO.UserOrProducerIdEmailForAdminDTO;
import by.c43.store.dto.commentDTO.DescriptionProductDTO;
import by.c43.store.dto.producerDTO.AllArgsProducerDTO;
import by.c43.store.dto.producerDTO.EmailPasswordProducerDTO;
import by.c43.store.dto.productDTO.AllArgsProductDTO;
import by.c43.store.dto.telephonesDTO.NumberIdTelDTO;
import by.c43.store.dto.telephonesDTO.NumberTelDTO;
import by.c43.store.dto.usersDTO.AllArgUsersDTO;
import by.c43.store.dto.usersDTO.EmailPasswordUsersDTO;
import by.c43.store.entity.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public static Telephone getIdTelIdDTO(NumberIdTelDTO numberTelDTO){
        return Telephone.builder()
                .id(numberTelDTO.getId())
                .number(numberTelDTO.getNumber())
                .build();
    }

    public static Product getAllArgsProductDTO(AllArgsProductDTO allArgsProductDTO, Producer producer){
        return Product.builder()
                .name(allArgsProductDTO.getName())
                .description(allArgsProductDTO.getDescription())
                .category(allArgsProductDTO.getCategory())
                .producer(producer)
                .picture(allArgsProductDTO.getPicture())
                .price(allArgsProductDTO.getPrice())
                .rating(Rating.builder().score(1).build())
                .reservedStatus(false)
                .saleStatus(true)
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

    public static Address getAllArgAddressDTO(ArgNoIdAddressDTO argNoIdAddressDTO){
        return Address.builder()
                .country(argNoIdAddressDTO.getCountry())
                .city(argNoIdAddressDTO.getCity())
                .street(argNoIdAddressDTO.getStreet())
                .home(argNoIdAddressDTO.getHome())
                .build();
    }

    public static Address getAllArgAddressIdDTO(AllArgsAddressDTO argAddressDTO){
        return Address.builder()
                .id(argAddressDTO.getId())
                .country(argAddressDTO.getCountry())
                .city(argAddressDTO.getCity())
                .street(argAddressDTO.getStreet())
                .home(argAddressDTO.getHome())
                .build();
    }

    public static CardDTO getCardDto(Product product, List<Comment> comments, Rating rating ){
        return CardDTO.builder()
                .product(product)
                .comments(comments)
                .rating(rating)
                .build();
    }

    public static UserCardInfoDTO getUserCard(User user){
        return UserCardInfoDTO.builder()
                .nameUser(user.getName())
                .emailUser(user.getEmail())
                .pictureUser(user.getPicture())
                .numberUser(user.getTelephone().getNumber())
                .build();
    }

    public static ProducerCardDTO getProducerCard(Producer producer){
        return ProducerCardDTO.builder()
                .nameProducer(producer.getName())
                .emailProducer(producer.getEmail())
                .descriptionProducer(producer.getDescription())
                .pictureProducer(producer.getPicture())
                .addressProducer(producer.getAddress())
                .telephonesProducer(producer.getTelephones())
                .build();
    }

    public static Comment getDescriptionProductDTO(DescriptionProductDTO descriptionProductUserDTO, User user){
        return Comment.builder()
                .description(descriptionProductUserDTO.getDescription())
                .time(Timestamp.valueOf(LocalDateTime.now()))
                .user(User.builder().id(user.getId()).build())
                .product(Product.builder()
                        .id(descriptionProductUserDTO.getProductId())
                        .build())
                .build();
    }

    public static List<UserOrProducerIdEmailForAdminDTO> getCardsUserForAdmin(List<User> users){
        List<UserOrProducerIdEmailForAdminDTO> userCards = new ArrayList<>();
        users.forEach(u -> userCards.add(UserOrProducerIdEmailForAdminDTO.builder().id(u.getId()).email(u.getEmail()).build()));
        return userCards;
    }

    public static List<UserOrProducerIdEmailForAdminDTO> getCardsProducerForAdmin(List<Producer> producers){
        List<UserOrProducerIdEmailForAdminDTO> producerCards = new ArrayList<>();
        producers.forEach(p -> producerCards.add(UserOrProducerIdEmailForAdminDTO.builder().id(p.getId()).email(p.getEmail()).build()));
        return producerCards;
    }
}
