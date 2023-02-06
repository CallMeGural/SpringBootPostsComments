package pl.fg.springpostcomment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.fg.springpostcomment.config.DataGenerator;

import java.io.IOException;

@SpringBootApplication
public class SpringPostCommentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPostCommentApplication.class, args);
	}

}
