package kr.co.polycube.backendtest.backendtestmaster._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.ErrorResponse;
import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.Exception400;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.regex.Pattern;

public class URLFilter implements Filter {

    private static final Pattern INVALID_PATTERN = Pattern.compile("[^a-zA-Z0-9?&=:/]");


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String queryString = request.getQueryString();
        String requestURI = request.getRequestURI();

        if (invalidCharacters(queryString) || invalidCharacters(requestURI)) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(400);
            response.getWriter().println(new ErrorResponse("잘못된 특수문자 요청"));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean invalidCharacters(String input) {
        return input != null && INVALID_PATTERN.matcher(input).find();
    }
}
