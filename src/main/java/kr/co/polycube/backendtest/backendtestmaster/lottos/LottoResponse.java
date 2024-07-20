package kr.co.polycube.backendtest.backendtestmaster.lottos;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class LottoResponse {

    @Data
    public static class SaveDTO {
        private List<Integer> numbers;

        public SaveDTO(Lotto lotto) {
            this.numbers = Arrays.asList(
                    lotto.getNumber1(),
                    lotto.getNumber2(),
                    lotto.getNumber3(),
                    lotto.getNumber4(),
                    lotto.getNumber5(),
                    lotto.getNumber6()
            );
        }

    }
}
