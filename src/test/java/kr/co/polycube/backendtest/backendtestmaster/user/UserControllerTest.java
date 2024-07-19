package kr.co.polycube.backendtest.backendtestmaster.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper om = new ObjectMapper();

    @Test
    public void saveUser_sucess_test() throws Exception {
        // given
        String name = "soyeon";
        UserRequest.SaveUserDTO reqestDTO = new UserRequest.SaveUserDTO();
        reqestDTO.setName(name);

        String reqestBody = om.writeValueAsString(reqestDTO);
        System.out.println(reqestBody);

        // when
        ResultActions actions = mvc.perform(
                post("/users")
                        .content(reqestBody)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.id").value(1));

    }

    @Test
    public void saveUser_valid_test() throws Exception {
        // given
        String name = "";
        UserRequest.SaveUserDTO reqestDTO = new UserRequest.SaveUserDTO();
        reqestDTO.setName(name);

        String reqestBody = om.writeValueAsString(reqestDTO);
        System.out.println(reqestBody);

        // when
        ResultActions actions = mvc.perform(
                post("/users")
                        .content(reqestBody)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.id").value(1));

    }
}
