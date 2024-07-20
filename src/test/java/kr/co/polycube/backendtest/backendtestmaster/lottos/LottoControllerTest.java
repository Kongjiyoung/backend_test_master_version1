package kr.co.polycube.backendtest.backendtestmaster.lottos;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.contains;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LottoControllerTest {

    @Autowired
    private MockMvc mvc;


    private ObjectMapper om = new ObjectMapper();

    @Test
    public void saveLotto_sucess_test() throws Exception {
        // given
        LottoRequest.SaveDTO requestDTO = new LottoRequest.SaveDTO();
        requestDTO.setNumber1(1);
        requestDTO.setNumber2(2);
        requestDTO.setNumber3(3);
        requestDTO.setNumber4(4);
        requestDTO.setNumber5(5);
        requestDTO.setNumber6(6);

        String reqestBody = om.writeValueAsString(requestDTO);
        System.out.println(reqestBody);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        // when
        ResultActions actions = mvc.perform(
                post("/lotto")
                        .content(reqestBody)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.numbers", contains(1, 2, 3, 4, 5, 6)));

    }

    @Test
    public void saveLotto_fail_test() throws Exception {
        // given
        LottoRequest.SaveDTO requestDTO = new LottoRequest.SaveDTO();
        requestDTO.setNumber1(1);
        requestDTO.setNumber2(2);
        requestDTO.setNumber3(3);
        requestDTO.setNumber4(4);
        requestDTO.setNumber5(5);
        requestDTO.setNumber6(5);

        String reqestBody = om.writeValueAsString(requestDTO);
        System.out.println(reqestBody);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // when
        ResultActions actions = mvc.perform(
                post("/lotto")
                        .content(reqestBody)
                        .contentType(MediaType.APPLICATION_JSON));
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);


        // then
        actions.andExpect(jsonPath("$.reason").value("로또 번호는 중복될 수 없습니다"));
    }

}
