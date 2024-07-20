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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;



    private ObjectMapper om = new ObjectMapper();

    @Test
    public void saveUser_sucess_test() throws Exception {
        // given
        String name = "soyeon";
        UserRequest.SaveUserDTO requestDTO = new UserRequest.SaveUserDTO();
        requestDTO.setName(name);

        String requestBody = om.writeValueAsString(requestDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                post("/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.id").value(2));

    }

    @Test
    public void saveUser_valid_test() throws Exception {
        // given
        String name = "";
        UserRequest.SaveUserDTO requestDTO = new UserRequest.SaveUserDTO();
        requestDTO.setName(name);

        String requestBody = om.writeValueAsString(requestDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                post("/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.reason").value("must not be empty : name"));

    }

    @Test
    public void findUser_sucess_test() throws Exception {
        // given
        Long id = 1L;

        // when
        ResultActions actions = mvc.perform(
                get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.id").value(1));
        actions.andExpect(jsonPath("$.name").value("jiyoung"));

    }

    @Test
    public void findUser_fail_test() throws Exception {
        // given
        Long id = 6L;

        // when
        ResultActions actions = mvc.perform(
                get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.reason").value("존재하지 않는 유저 번호입니다"));


    }

    @Test
    public void updateUser_sucess_test() throws Exception {
        // given
        Long id = 1L;
        String name = "soyeon";
        UserRequest.SaveUserDTO requestDTO = new UserRequest.SaveUserDTO();
        requestDTO.setName(name);

        String requestBody = om.writeValueAsString(requestDTO);
//        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                put("/users/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.id").value(1));
        actions.andExpect(jsonPath("$.name").value("soyeon"));

    }

    @Test
    public void updateUser_fail_test() throws Exception {
        // given
        Long id = 6L;
        String name = "soyeon";
        UserRequest.SaveUserDTO requestDTO = new UserRequest.SaveUserDTO();
        requestDTO.setName(name);

        String requestBody = om.writeValueAsString(requestDTO);
        System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                put("/users/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.reason").value("존재하지 않는 유저 번호입니다"));

    }

    @Test
    public void filterTest_sucess_test() throws Exception {
        // given
        Long id = 1L;

        // when
        ResultActions actions = mvc.perform(
                get("/users/{id}?name=test!!", id)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseBody);


        // then
        actions.andExpect(status().isBadRequest());

    }
}