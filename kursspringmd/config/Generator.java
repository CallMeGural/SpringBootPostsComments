package pl.fg.kursspringmd.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Generator {
    public void generatePostsAndComments() throws IOException {
        File dataSql = new File("src/main/resources/data.sql");
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataSql,true));
        writer.write("");
        for(int i=1;i<=100;i++) {
            writer.append("insert into post(id, title, content, created) " + "values (").append(String.valueOf(i)).append(", 'Test post ").append(String.valueOf(i)).append("', 'Content ").append(String.valueOf(i)).append("', '").append(String.valueOf(LocalDateTime.now().minusDays(100 - i))).append("');\n");
        }
        for(int i=1;i<=100;i++) {
            int postId = 1+i/10;
            writer.append("insert into comment(id, post_id, content, created) " + "values (").append(String.valueOf(i)).append(", ").append(String.valueOf(postId)).append(", 'Comment ").append(String.valueOf(i)).append("', '").append(String.valueOf(LocalDateTime.now().minusDays(100 - i))).append("');\n");
        }
        writer.close();
    }
}
