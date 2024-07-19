package kr.co.polycube.backendtest.backendtestmaster.lottos;

import kr.co.polycube.backendtest.backendtestmaster.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LottosRepository extends JpaRepository<Lottos, Integer> {

    Lottos findById(@Param("id") Long id);

}
