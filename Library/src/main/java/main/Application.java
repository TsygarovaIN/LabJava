package main;

import main.entity.Books;
import main.repos.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner test (final BooksRepository repository) {
//       return args -> {
//            repository.save(new Books( "Witcher", 5, 1));
//
//            repository.save(new Books( "Witcher2", 9, 2));
//
//            for(Books books : repository.findAll()) {
//                log.info("The books is: " + books.toString());
//            }
//        };
//    }

}
