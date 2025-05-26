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

/**
 * Test class for the ControllerUser.
 * This class tests the CRUD operations for User entities.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class ControllerUserTests {

    /**
     * MockMvc instance for testing the controller.
     * This instance is used to perform requests and assert responses.
     */
    @Autowired
    private MockMvc mvc;

    /**
     * Repository for User operations.
     * This repository is used to interact with the database for User entities.
     */
    @Autowired
    private RepoUser repoUser;

    /**
     * User instance used for testing.
     * This instance is created before each test to ensure a consistent state.
     */
    private User user;

    /**
     * Sets up the test environment by creating a new User instance.
     * This method is called before each test to ensure a fresh state.
     */
    @BeforeEach
    public void setUp(){
        user = new User("ROUX","Lilian");
        user = repoUser.save(user);
    }

    /**
     * Tests the retrieval of a user by ID.
     * This test checks if the user can be successfully retrieved from the database.
     */
    @Test
    public void getUserByid() throws Exception {
        mvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())));
    }

    /**
     * Tests the retrieval of a user by ID that does not exist.
     * This test checks if an exception is thrown when trying to retrieve a non-existent user.
     */
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

    /**
     * Tests the deletion of a user by ID.
     * This test checks if the user can be successfully deleted from the database.
     */
    @Test
    public void deleteUserByid() throws Exception {
        mvc.perform(delete("/api/user/delete/1"))
                .andExpect(status().isOk());
    }
}
