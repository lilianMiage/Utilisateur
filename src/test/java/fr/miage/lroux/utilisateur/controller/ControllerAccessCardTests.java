package fr.miage.lroux.utilisateur.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.lroux.utilisateur.entity.AccessCard;
import fr.miage.lroux.utilisateur.repository.RepoAccessCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test controller for CRUD operations on AccessCard.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ControllerAccessCardTests {

    /**
     * MockMvc instance for testing the controller.
     */
    @Autowired
    private MockMvc mvc;

    /**
     * Repository for AccessCard operations.
     * This repository is used to interact with the database for AccessCard entities.
     */
    @Autowired
    private RepoAccessCard repoAccessCard;

    /**
     * AccessCard instance used for testing.
     * This instance is created before each test to ensure a consistent state.
     */
    private AccessCard accessCard;

    /**
     * Sets up the test environment by creating a new AccessCard instance.
     * This method is called before each test to ensure a fresh state.
     */
    @BeforeEach
    public void setUp(){
        accessCard = new AccessCard(new Random().nextInt());
        accessCard = repoAccessCard.save(accessCard);
    }

    /**
     * Tests the retrieval of an AccessCard by its ID.
     * This test checks if the correct AccessCard is returned with the expected password.
     */
    @Test
    public void getAccessCardByid() throws Exception {
        mvc.perform(get("/api/accessCard/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password", is(accessCard.getPassword())));
    }

    /**
     * Tests the creation of a new AccessCard.
     * This test checks if the AccessCard is created successfully and the returned password matches.
     */
    @Test
    public void createAccessCard() throws Exception{
        AccessCard accessCardObject = new AccessCard(new Random().nextInt());
        ObjectMapper om = new ObjectMapper();
        String accessCardJson = om.writeValueAsString(accessCardObject);
        mvc.perform(post("/api/accessCard/create")
                        .contentType("application/json")
                        .content(accessCardJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password", is(accessCardObject.getPassword())));
    }

    /**
     * Tests the deletion of an AccessCard by its ID.
     * This test checks if the AccessCard is deleted successfully.
     */
    @Test
    public void deleteAccessByid() throws Exception {
        mvc.perform(delete("/api/accessCard/delete/1"))
                .andExpect(status().isOk());
    }
}