package project.workable.movieRama.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project.workable.movieRama.domain.MUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findUserByUsername() {
        MUser user = new MUser("David","7");
        userRepository.save(user);

        Optional<MUser> underTest = userRepository.findUserByUsername(user.getUsername());

        assertTrue(underTest.isPresent());
        assertEquals(user.getUsername(), underTest.get().getUsername());
    }

}