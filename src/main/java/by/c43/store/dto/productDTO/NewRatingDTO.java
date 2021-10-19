package by.c43.store.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewRatingDTO {
    private long prodId;
    @Min(1)
    @Max(5)
    private int score;
}
