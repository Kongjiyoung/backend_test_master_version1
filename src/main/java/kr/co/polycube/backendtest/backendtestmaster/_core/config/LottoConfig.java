//package kr.co.polycube.backendtest.backendtestmaster._core.config;
//
//import kr.co.polycube.backendtest.backendtestmaster.lottos.Lotto;
//import kr.co.polycube.backendtest.backendtestmaster.lottos.LottoRepository;
//import kr.co.polycube.backendtest.backendtestmaster.lottos.LottoService;
//import kr.co.polycube.backendtest.backendtestmaster.winner.Winner;
//import kr.co.polycube.backendtest.backendtestmaster.winner.WinnerRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//@Configuration
//public class LottoConfig {
//
//    private final LottoService lottoService;
//    private final WinnerRepository winnerRepository;
//    private final LottoRepository lottoRepository;
//
//    @Scheduled(cron = "0 0 0 * * SUN")
//    @Transactional
//    public void runScheduledTask() {
//        log.info("스케줄러 시작");
//
//        try {
//            List<Lotto> lottos = lottoRepository.findAll();
//            List<Integer> winningNumbers = lottoService.winningLottoNumbers();
//            log.info("당첨 번호 : " + winningNumbers);
//
//            for (Lotto lotto : lottos) {
//                int rank = lottoService.winningRank(lotto, winningNumbers);
//                if (rank > 0) {
//                    Winner winner = new Winner();
//                    winner.setRank(rank);
//                    winner.setLotto(lotto);
//                    winnerRepository.save(winner);
//                }
//            }
//
//            log.info("스케줄러 성공");
//        } catch (Exception e) {
//            log.error("스케 줄러 에러", e);
//        }
//    }
//}
