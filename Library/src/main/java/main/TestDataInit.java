package main;

import main.entity.BookTypes;
import main.entity.User;
import main.repos.BookTypesRepository;
import main.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    BookTypesRepository bookTypesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder pwdEncoder;

    @Override
    public void run(String... args) throws Exception {
        bookTypesRepository.save(new BookTypes("fun", 10L, 22L, 1000L));
        bookTypesRepository.save(new BookTypes("comedy", 25L, 77L, 313L));

        userRepository.save(new User("user", pwdEncoder.encode("pwd"),
                Collections.singletonList("ROLE_USER")));
        userRepository.save(new User("admin", pwdEncoder.encode("apwd"),
                Collections.singletonList("ROLE_ADMIN")));
    }
}
