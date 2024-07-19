package kr.co.polycube.backendtest.backendtestmaster.winner;

import kr.co.polycube.backendtest.backendtestmaster.lottos.Lottos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Integer> {
}
