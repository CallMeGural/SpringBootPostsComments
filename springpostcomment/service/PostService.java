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

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final static int PAGE_SIZE = 2;

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalIdentifierException("Post does not exist"));
    }

    public List<Post> getPosts(int page) {
        return postRepository.findAllPosts(PageRequest.of(page,PAGE_SIZE));
    }

    public Post createNewPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post editedPost = postRepository.findById(post.getId()).orElseThrow(() -> new IllegalIdentifierException("Post does not exist"));
        if(post.getContent() != null || !post.getContent().isBlank())
            editedPost.setContent(post.getContent());
        if(post.getTitle() != null || !post.getTitle().isBlank())
        editedPost.setTitle(post.getTitle());
        return editedPost;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
