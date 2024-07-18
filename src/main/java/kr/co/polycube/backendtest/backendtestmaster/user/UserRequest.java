package kr.co.polycube.backendtest.backendtestmaster.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

public class UserRequest {

    @Data
    public static class SaveUserDTO{
        @NotEmpty
        private String name;

        public User toEntity(){
            return User.builder()
                    .name(name)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        @NotEmpty
        private String name;
    }
}
