package kr.co.polycube.backendtest.backendtestmaster.lottos;

import lombok.Data;

public class LottosRequest {

    @Data
    public static class SaveDTO {
        private Integer number1;
        private Integer number2;
        private Integer number3;
        private Integer number4;
        private Integer number5;
        private Integer number6;

        public Lottos toEntity(){
            return Lottos.builder()
                    .number1(number1)
                    .number2(number2)
                    .number3(number3)
                    .number4(number4)
                    .number5(number5)
                    .number6(number6)
                    .build();
        }
    }
}
