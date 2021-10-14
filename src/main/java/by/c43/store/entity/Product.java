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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producer_id")
    @Column(nullable = false)
    private Producer producer;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne(fetch = FetchType.EAGER)
    private Rating rating;

    @Column(nullable = false)
    private boolean reservedStatus;

    @Column(nullable = false)
    private boolean saleStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
                ", owner=" + owner +
                ", reservedStatus=" + reservedStatus +
                ", saleStatus=" + saleStatus +
                '}';
    }
}
