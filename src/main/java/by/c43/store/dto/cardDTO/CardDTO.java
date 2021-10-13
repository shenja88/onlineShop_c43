package by.c43.store.dto.cardDTO;

import by.c43.store.entity.Comment;
import by.c43.store.entity.Product;
import by.c43.store.entity.Rating;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CardDTO {


    private Product product;
    private List<Comment> comments;
    private Rating rating;

}
