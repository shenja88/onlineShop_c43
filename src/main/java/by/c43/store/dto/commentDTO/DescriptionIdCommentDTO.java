package by.c43.store.dto.commentDTO;

import by.c43.store.utils.ConstraintsMessageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionIdCommentDTO {
    private long commentId;

    @NotBlank(message = ConstraintsMessageManager.NOT_EMPTY_ERROR)
    @NotNull
    @NotEmpty
    private String description;
}
