package ru.podkovyrov.denis.routiin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.podkovyrov.denis.routiin.entities.User;
import ru.podkovyrov.denis.routiin.payloads.UserPostRequest;
import ru.podkovyrov.denis.routiin.security.TokenProvider;
import ru.podkovyrov.denis.routiin.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.podkovyrov.denis.routiin.controller.api.v1.ControllerConstants.API_VERSION;
import static ru.podkovyrov.denis.routiin.util.Convert.asJsonString;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/create-user.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    public void getAllUsers() throws Exception{

        this.mockMvc.perform(get("/v1/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());

    }

    @Test
    public void getMeUser() throws Exception {
        User user = userService.findById(1L).get();

        String token = tokenProvider.createToken(user.getId());

        this.mockMvc.perform(get("/v1/user/me")
                .header("Authorization", "Bearer "+token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value(user.getLogin()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(user.getLastName()));
    }

    @Test
    public void getNotFoundUser() throws Exception {
        this.mockMvc.perform(get("/"+API_VERSION+"user/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void securityUserTest() throws Exception {
        this.mockMvc.perform(get("/v1/user/me")
                .header("Authorization", "Bearer "+ "0"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void updateUserInfo() throws Exception {
        User user = userService.findById(1L).get();

        String token = tokenProvider.createToken(user.getId());
        String newLogin = "Superman";
        this.mockMvc.perform(
                post("/v1/user/me")
                        .header("Authorization", "Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new UserPostRequest(null, null, newLogin))))
                        .andExpect(status().isOk());

        user = userService.findById(1L).get();

        assertEquals(user.getLogin(), (newLogin));

        this.mockMvc.perform(get("/v1/user/me")
                .header("Authorization", "Bearer "+token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value(user.getLogin()));

    }
}
