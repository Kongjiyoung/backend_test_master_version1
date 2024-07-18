package kr.co.polycube.backendtest.backendtestmaster._core.errors;

import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.ErrorResponse;
import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.Exception400;
import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.Exception404;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<ErrorResponse> ex400(Exception400 e){
        ErrorResponse responseDTO = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST); // http body, http header
    }
    @ExceptionHandler(Exception404.class)
    public ResponseEntity<ErrorResponse> ex404(Exception404 e){
        ErrorResponse responseDTO = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}
