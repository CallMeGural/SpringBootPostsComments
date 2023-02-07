package pl.fg.springpostcomment.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDto {
    private long id;
    private String content;
}
