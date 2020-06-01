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
import ru.podkovyrov.denis.routiin.payloads.DayInterval;
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
@Sql(value = {"/create-card.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-card.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CardTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;

    @Test
    public void getAllUserCards() throws Exception {
        User user = userService.findById(1L).get();

        String token = tokenProvider.createToken(user.getId());

        this.mockMvc.perform(post("/v1/user/me/cards/from/interval")
                .header("Authorization", "Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(
                                new DayInterval("2020-05-31T21:00:00.000Z",
                                        "2020-06-01T20:59:00.000Z"
                                ))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

}
