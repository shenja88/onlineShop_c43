package by.c43.store.entity;


import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    private long id;
    private String description;
    private Timestamp time;
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(description, comment.description) && Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, user);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", user=" + user +
                '}';
    }
}
