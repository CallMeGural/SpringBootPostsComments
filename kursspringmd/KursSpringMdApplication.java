package pl.fg.kursspringmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;

@SpringBootApplication
@EnableCaching
public class KursSpringMdApplication {

    public static void main(String[] args) throws IOException {
        //new Generator().generatePostsAndComments();
        SpringApplication.run(KursSpringMdApplication.class, args);
    }

}
