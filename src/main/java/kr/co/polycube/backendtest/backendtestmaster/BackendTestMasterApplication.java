package kr.co.polycube.backendtest.backendtestmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BackendTestMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendTestMasterApplication.class, args);
    }

}
