package kr.co.polycube.backendtest.backendtestmaster.lottos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LottoRepository extends JpaRepository<Lotto, Integer> {

    Lotto findById(@Param("id") Long id);

}
