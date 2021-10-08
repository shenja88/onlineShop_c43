package by.c43.store.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    private long id;
    private double score;
    private List<Long> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return id == rating.id && Double.compare(rating.score, score) == 0 && Objects.equals(users, rating.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, users);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", score=" + score +
                ", users=" + users +
                '}';
    }
}
