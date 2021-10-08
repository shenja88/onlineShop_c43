package by.c43.store.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private long id;
    private String name;
    private String description;
    private CategoryOfProduct category;
    private Producer producer;
    private String picture;
    private double price;
    private Rating rating;
    private List<Comment> comments;
    private User owner;
    private boolean reservedStatus;
    private boolean saleStatus;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(description, product.description) && category == product.category && Objects.equals(producer, product.producer) && Objects.equals(owner, product.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, category, producer, owner);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", producer=" + producer +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", comments=" + comments +
                ", owner=" + owner +
                ", reservedStatus=" + reservedStatus +
                ", saleStatus=" + saleStatus +
                '}';
    }
}
