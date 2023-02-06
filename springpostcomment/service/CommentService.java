package pl.fg.springpostcomment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.fg.springpostcomment.model.Comment;
import pl.fg.springpostcomment.model.Post;
import pl.fg.springpostcomment.repository.CommentRepository;
import pl.fg.springpostcomment.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private static final int PAGE_SIZE = 2;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<Comment> getCommentsFromPost(long id,int page) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalIdentifierException("Post does not exist"));
        return commentRepository.findAllByPost(post, PageRequest.of(page,PAGE_SIZE));
    }

    public Comment getSingleCommentFromPost(long commentId, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalIdentifierException("Post does not exist"));
        return commentRepository.findByPost(post,commentId).orElseThrow(() -> new IllegalIdentifierException("Comment does not exist"));
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public void editComment(long id,String content) {
        Comment editedComment = commentRepository.findById(id).orElseThrow(() -> new IllegalIdentifierException("Comment does not exist"));
        if(content != null || !content.isBlank())
            editedComment.setContent(content);
    }
}
