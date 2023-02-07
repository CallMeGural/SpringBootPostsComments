package pl.fg.springpostcomment.dtos;

import pl.fg.springpostcomment.model.Comment;
import pl.fg.springpostcomment.model.CommentDto;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    private CommentMapper() {}

    public static List<CommentDto> mapCommentsToDtos(List<Comment> comments) {
        return comments
                .stream()
                .map(comment -> mapCommentToDto(comment))
                .collect(Collectors.toList());
    }

    private static CommentDto mapCommentToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .build();
    }
}
