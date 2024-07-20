package kr.co.polycube.backendtest.backendtestmaster.winner;

import jakarta.persistence.*;
import kr.co.polycube.backendtest.backendtestmaster.lottos.Lotto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "winner_tb")
@Entity
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lotto lotto;

    private Integer rank;

    @Builder
    public Winner(Long id, Lotto lotto, Integer rank) {
        this.id = id;
        this.lotto = lotto;
        this.rank = rank;
    }
}
