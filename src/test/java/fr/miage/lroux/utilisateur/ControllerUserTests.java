package fr.miage.lroux.utilisateur;

import fr.miage.lroux.utilisateur.entity.User;
import fr.miage.lroux.utilisateur.repository.RepoUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        user = new User(0L,"ROUX","Lilian");
        user = repoUser.save(user);
    }

    @Test
    public void getUserByid() throws Exception {
        mvc.perform(get("/api/user/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())));
    }

}
