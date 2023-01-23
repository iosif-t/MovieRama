package project.iosif.movieRama.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.iosif.movieRama.security.SecurityUserDetailsService;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class UserControllerTest {

    @Mock
    private SecurityUserDetailsService userDetailsManager;

    private UserController userController;

    @BeforeEach
    void setUp() {
      userController = new UserController(userDetailsManager);
    }

    @Test
    void mockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("main/login"));
    }

    @Test
    void login() {
        assertEquals("main/login" , userController.login());
    }

    @Test
    void addUser(){
       assertThrows(NullPointerException.class,
               () -> userController.addUser(new HashMap<>()));
    }

}