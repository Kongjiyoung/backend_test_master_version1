package kr.co.polycube.backendtest.backendtestmaster.lottos;

import kr.co.polycube.backendtest.backendtestmaster._core.errors.exception.Exception400;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LottoService {
    private final LottoRepository lottoRepository;

    public LottoResponse.SaveDTO save(LottoRequest.SaveDTO requestDTO) {
        int[] numbers = {
                requestDTO.getNumber1(),
                requestDTO.getNumber2(),
                requestDTO.getNumber3(),
                requestDTO.getNumber4(),
                requestDTO.getNumber5(),
                requestDTO.getNumber6()
        };

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    throw new Exception400("로또 번호는 중복될 수 없습니다");
                }
            }
        }

        Lotto lotto = lottoRepository.save(requestDTO.toEntity());

        return new LottoResponse.SaveDTO(lotto);
    }

    public List<Integer> winningLottoNumbers() {
        return new Random().ints(1, 46)
                .distinct()
                .limit(6)
                .boxed()
                .collect(Collectors.toList());
    }

    public int winningRank(Lotto lotto, List<Integer> winningNumbers) {
        List<Integer> numbers = List.of(
                lotto.getNumber1(),
                lotto.getNumber2(),
                lotto.getNumber3(),
                lotto.getNumber4(),
                lotto.getNumber5(),
                lotto.getNumber6()
        );

        int matches = (int) numbers.stream().filter(winningNumbers::contains).count();

        if (matches == 6) {
            return 1; // 1등
        } else if (matches == 5) {
            return 2; // 2등
        } else if (matches == 4) {
            return 3; // 3등
        } else if (matches == 3) {
            return 4; // 4등
        } else if (matches == 2) {
            return 5; // 5등
        } else {
            return 0; // 당첨되지 않음
        }
    }
}
