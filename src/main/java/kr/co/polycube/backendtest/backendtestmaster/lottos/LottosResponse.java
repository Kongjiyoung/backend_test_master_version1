package kr.co.polycube.backendtest.backendtestmaster.lottos;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class LottosResponse {

    @Data
    public static class SaveDTO {
        private List<Integer> numbers;

        public SaveDTO(Lottos lottos) {
            this.numbers = Arrays.asList(
                    lottos.getNumber1(),
                    lottos.getNumber2(),
                    lottos.getNumber3(),
                    lottos.getNumber4(),
                    lottos.getNumber5(),
                    lottos.getNumber6()
            );
        }

    }
}
