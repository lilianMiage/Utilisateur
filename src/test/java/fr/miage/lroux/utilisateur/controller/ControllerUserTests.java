package fr.miage.lroux.utilisateur.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.lroux.utilisateur.entity.User;
import fr.miage.lroux.utilisateur.repository.RepoUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class ControllerUserTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RepoUser repoUser;
    private User user;

    @BeforeEach
    public void setUp(){
        user = new User("ROUX","Lilian");
        user = repoUser.save(user);
    }

    @Test
    public void getUserByid() throws Exception {
        mvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())));
    }

    @Test
    public void createUser() throws Exception{
        User userObject = new User("TUDELA","Yannis");
        ObjectMapper om = new ObjectMapper();
        String userJson = om.writeValueAsString(userObject);
        mvc.perform(post("/api/user/create")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(userObject.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(userObject.getLastName())));
    }

    @Test
    public void deleteUserByid() throws Exception {
        mvc.perform(delete("/api/user/delete/1"))
                .andExpect(status().isOk());
    }
}
