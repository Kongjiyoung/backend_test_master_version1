package kr.co.polycube.backendtest.backendtestmaster._core.errors;

import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Component
@Aspect
public class ValidationExceptionHandler {

    // 유효성 검사 자동화
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void validCheck(JoinPoint jp){
        Object[] args = jp.getArgs();

        for(Object arg : args){
            if(arg instanceof Errors errors){

                if(errors.hasErrors()){
                    for (FieldError error : errors.getFieldErrors()){
                        throw new Exception400(error.getDefaultMessage()+" : "+error.getField());
                    }
                }
            }

        }
    }
}