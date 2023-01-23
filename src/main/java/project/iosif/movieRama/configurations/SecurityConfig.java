package project.iosif.movieRama.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for login page
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * @param http
     * @return Returns http
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/sortByLike").permitAll()
                .antMatchers("/sortByHate").permitAll()
                .antMatchers("/sortByDate").permitAll()
                .antMatchers("/userMovies").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .and().logout().logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true).permitAll();;
        return http.build();
    }

}
