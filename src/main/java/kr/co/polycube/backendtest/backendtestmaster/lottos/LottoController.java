package kr.co.polycube.backendtest.backendtestmaster.lottos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LottoController {

    private final LottoService lottoService;

    @PostMapping("/lotto")
    public ResponseEntity<?> saveLotto(@RequestBody LottoRequest.SaveDTO requestDTO) {

        LottoResponse.SaveDTO responseDTO= lottoService.save(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }
}
