package pl.fg.kursspringmd.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.fg.kursspringmd.model.Comment;
import pl.fg.kursspringmd.model.Post;
import pl.fg.kursspringmd.repository.CommentRepository;
import pl.fg.kursspringmd.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(Integer page, Sort.Direction sort) {
        return postRepository.findAllPosts(
                PageRequest.of(
                        page,PAGE_SIZE, Sort.by(sort,"id")));
    }

    @Cacheable(cacheNames = "SinglePost", key = "#id")
    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Cacheable(cacheNames = "PostWithComments")
    public List<Post> getPostsWithComments(Integer page, Sort.Direction sort) {
        List<Post> posts = postRepository.findAllPosts(
                PageRequest.of(
                        page,PAGE_SIZE,Sort.by(sort, "id")));
        List<Long> ids = posts.stream()
                .map(post -> post.getId())
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        posts.forEach(post -> post.setComments(extractComments(comments, post.getId())));
        return posts;
    }

    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    @CachePut(cacheNames = "SinglePost", key = "#result.id") //update tresci mimo zapisania w cache'u
    public Post editPost(Post post) {
        Post editedPost = postRepository.findById(post.getId()).orElseThrow();
        editedPost.setTitle(post.getTitle());
        editedPost.setContent(post.getContent());
        return editedPost;
    }

    @CacheEvict(cacheNames = "SinglePost") //usuniecie z cache'a
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @CacheEvict(cacheNames = "PostsWithComments") //czyszczenie calego cache'a; musi byc wywolana z innego bean'a
    public void clearPostsWithComments() {}
}
