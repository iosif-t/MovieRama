package project.workable.movieRama.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.workable.movieRama.domain.MUser;
import project.workable.movieRama.repositories.UserRepository;

import java.util.Optional;

/**
 * Security service for {@link MUser}
 */
@Service
public class SecurityUserDetailsService {

    /**
     * user repository
     */
    private final UserRepository userRepository;

    /**
     * Constructor
     * @param userRepository
     */
    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user by username
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public MUser loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));
    }

    /**
     * Creates new user
     * @param user
     */
    public void createUser(MUser user) {
        Optional<MUser> existingUser = userRepository.findUserByUsername(user.getUsername());
        if(!existingUser.isPresent())
            userRepository.save(user);
        else throw new BadCredentialsException("User already exists");
    }

}
