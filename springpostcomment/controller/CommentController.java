package pl.fg.springpostcomment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.fg.springpostcomment.model.Comment;
import pl.fg.springpostcomment.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/posts/{id}")
    public List<Comment> getCommentsFromPost(@PathVariable long id,int page) {
        return commentService.getCommentsFromPost(id,page);
    }

    @GetMapping("/comments/{commentId}/posts/{postId}")
    public Comment getSingleCommentFromPost(@PathVariable long commentId, @PathVariable long postId) {
        return commentService.getSingleCommentFromPost(commentId,postId);
    }

    @PostMapping("/comments")
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/comments/{id}")
    public void editComment(@PathVariable long id, @RequestBody String content) {
        commentService.editComment(id,content);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable long id) {
        commentService.deleteComment(id);
    }
}
