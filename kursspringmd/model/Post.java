package pl.fg.kursspringmd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
//@NamedEntityGraph(name = "Post"
//        , attributeNodes =
//        @NamedAttributeNode("comments")
//)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="postId", updatable = false, insertable = false)
    private List<Comment> comments;
}
