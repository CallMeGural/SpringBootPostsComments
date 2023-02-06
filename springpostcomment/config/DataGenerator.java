package pl.fg.springpostcomment.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.fg.springpostcomment.model.Comment;
import pl.fg.springpostcomment.model.Post;
import pl.fg.springpostcomment.repository.CommentRepository;
import pl.fg.springpostcomment.repository.PostRepository;

import java.util.List;

@Component
public class DataGenerator {

     @Bean
    CommandLineRunner commandLineRunner(PostRepository postRepository, CommentRepository commentRepository) {
        return args -> {
            Post p1 = new Post("Title 1","Content 1");
            Post p2 = new Post("Title 2","Content 2");
            Post p3 = new Post("Title 3","Content 3");
            Post p4 = new Post("Title 4","Content 4");
            postRepository.saveAll(List.of(p1,p2,p3,p4));
            Comment c1 = new Comment("Content 1",p1);
            Comment c2 = new Comment("Content 2",p1);
            Comment c3 = new Comment("Content 3",p1);
            Comment c4 = new Comment("Content 4",p2);
            Comment c5 = new Comment("Content 5",p2);
            Comment c6 = new Comment("Content 6",p2);
            Comment c7 = new Comment("Content 7",p3);
            Comment c8 = new Comment("Content 8",p3);
            Comment c9 = new Comment("Content 9",p3);
            Comment c10 = new Comment("Content 10",p4);
            Comment c11 = new Comment("Content 11",p4);
            Comment c12 = new Comment("Content 12",p4);
            commentRepository.saveAll(List.of(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12));
        };
    }
}
