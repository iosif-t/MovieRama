package project.workable.movieRama.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import project.workable.movieRama.domain.MUser;

/**
 * Authentication provider
 */
@Component
public class AuthProvider implements AuthenticationProvider {

    /**
     * Security details service
     */
    private final SecurityUserDetailsService securityUserDetailsService;

    /**
     * @param securityUserDetailsService
     */
    public AuthProvider(SecurityUserDetailsService securityUserDetailsService) {
        this.securityUserDetailsService = securityUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = (String) authentication.getCredentials();
        MUser user = securityUserDetailsService.loadUserByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("User doesnt exist");
        } else if (!user.getPassword().equals(password)) {
            throw  new BadCredentialsException("Wrong password");
        }

        return new UsernamePasswordAuthenticationToken(username,password,authentication.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
