package pl.fg.kursspringmd.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.fg.kursspringmd.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("select p from Post p where p.title = ?1")
    List<Post> findAllByTitle(String title);

   /* @Query("select p from Post p "+
    "left join fetch p.comments") //left join fetch eliminuje problem n+1; problem ze stronicowaniem*/
    @EntityGraph(/*value = "Post.comments"*/attributePaths = "comments")
    @Query("select p from Post p")
    List<Post> findAllPostsWithComments(Pageable page);


    @Query("select p from Post p")
    List<Post> findAllPosts(Pageable page);
}
