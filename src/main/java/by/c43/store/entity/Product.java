package by.c43.store.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private CategoryOfProduct category;

    @ManyToOne
    @JoinColumn
    @Column(nullable = false)
    private Producer producer;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private double price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Rating rating;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    @Singular
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User owner;

    @Column(nullable = false)
    private boolean reservedStatus;

    @Column(nullable = false)
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
