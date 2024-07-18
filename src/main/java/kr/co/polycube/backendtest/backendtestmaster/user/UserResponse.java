package kr.co.polycube.backendtest.backendtestmaster.user;


import lombok.Data;

public class UserResponse {

    @Data
    public static class UserSaveDTO{
        private Long Id;

        public UserSaveDTO(User user) {
            Id = user.getId();
        }
    }
}
