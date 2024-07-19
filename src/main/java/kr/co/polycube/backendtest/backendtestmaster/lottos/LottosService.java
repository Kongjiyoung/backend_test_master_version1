package kr.co.polycube.backendtest.backendtestmaster.lottos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LottosService {
    private final LottosRepository lottosRepository;

    public LottosResponse.SaveDTO save(LottosRequest.SaveDTO requestDTO) {
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
                    throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
                }
            }
        }

        Lottos lottos = lottosRepository.save(requestDTO.toEntity());

        return new LottosResponse.SaveDTO(lottos);
    }

    public List<Integer> generateLottoNumbers() {
        return new Random().ints(1, 46)
                .distinct()
                .limit(6)
                .boxed()
                .collect(Collectors.toList());
    }

    public int calculateRank(Lottos lottos, List<Integer> winningNumbers) {
        List<Integer> numbers = List.of(
                lottos.getNumber1(),
                lottos.getNumber2(),
                lottos.getNumber3(),
                lottos.getNumber4(),
                lottos.getNumber5(),
                lottos.getNumber6()
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
