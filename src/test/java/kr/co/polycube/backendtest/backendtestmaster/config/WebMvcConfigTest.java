package kr.co.polycube.backendtest.backendtestmaster._core.config;

import kr.co.polycube.backendtest.backendtestmaster._core.filter.URLFilter;
import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
@ComponentScan(basePackages = "kr.co.polycube.backendtest.backendtestmaster._core.config")
public class WebMvcConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebMvcConfig webMvcConfig;

    @Test
    public void testCorsFilterRegistration() {
        FilterRegistrationBean<URLFilter> filterRegistrationBean = webMvcConfig.corsFilter();

        assertThat(filterRegistrationBean).isNotNull();
        assertThat(filterRegistrationBean.getFilter()).isInstanceOf(URLFilter.class);
        assertThat(filterRegistrationBean.getUrlPatterns()).containsExactly("/*");
        assertThat(filterRegistrationBean.getOrder()).isEqualTo(0);
    }

    @Test
    public void testInvalidCharactersInUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test?param=invalid@char"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 특수문자 요청"))
                .andDo(print());
    }

    @Test
    public void testValidUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test?param=valid123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
