package kr.co.polycube.backendtest.backendtestmaster._core.errors.exception;

import lombok.Data;

@Data
public class Exception404 extends RuntimeException{

    public Exception404(String msg) {
        super(msg);
    }
}
