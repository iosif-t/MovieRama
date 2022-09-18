package project.workable.movieRama.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.workable.movieRama.domain.MUser;
import project.workable.movieRama.security.SecurityUserDetailsService;

import java.util.Map;

/**
 * Controller class for {@link MUser}
 */
@Controller
public class UserController {

    /**
     * Security user details service
     */
    private final SecurityUserDetailsService userDetailsManager;

    /**
     * Constructor
     * @param userDetailsManager
     */
    public UserController(SecurityUserDetailsService userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    /**
     * @return Returns login page
     */
    @GetMapping("/login")
    public String login() {
        return "main/login";
    }

    /**
     * @param body
     * @return Returns redirects to /
     */
    @PostMapping(value = "/signup")
    public String addUser(@RequestParam Map<String, String> body) {
        MUser user = new MUser();
        user.setUsername(body.get("username"));
        user.setPassword(body.get("password"));
        userDetailsManager.createUser(user);
        return "redirect:/";
    }

    /**
     * @param model
     * @return Returns signup page
     */
    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user",new MUser());
        return "main/signup";
    }

}
