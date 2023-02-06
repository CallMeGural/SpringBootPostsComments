package pl.fg.springpostcomment.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.fg.springpostcomment.model.Comment;
import pl.fg.springpostcomment.model.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPost(Post post, Pageable pageable);

    @Query("select c from Comment c where c.post = ?1 and c.id =?2")
    Optional<Comment> findByPost(Post post, long id);
}
