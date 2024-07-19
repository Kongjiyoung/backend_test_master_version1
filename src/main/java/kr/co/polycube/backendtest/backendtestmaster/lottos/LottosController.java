package kr.co.polycube.backendtest.backendtestmaster.lottos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LottosController {

    private final LottosService lottosService;

    @PostMapping("/lottos")
    public ResponseEntity<?> save(@RequestBody LottosRequest.SaveDTO reqestDTO) {

        LottosResponse.SaveDTO responseDTO= lottosService.save(reqestDTO);

        return ResponseEntity.ok(responseDTO);
    }
}
