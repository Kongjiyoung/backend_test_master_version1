package kr.co.polycube.backendtest.backendtestmaster.user;


import lombok.Data;

public class UserResponse {


    @Data
    public static class SaveUserDTO{
        private Long Id;

        public SaveUserDTO(User user) {
            Id = user.getId();
        }
    }

    @Data
    public static class findUserDTO{
        private Long Id;
        private String name;

        public findUserDTO(User user) {
            Id = user.getId();
            name = user.getName();
        }
    }


    @Data
    public static class UpdateUserDTO{
        private Long Id;
        private String name;

        public UpdateUserDTO(User user) {
            Id = user.getId();
            name = user.getName();
        }
    }
}
