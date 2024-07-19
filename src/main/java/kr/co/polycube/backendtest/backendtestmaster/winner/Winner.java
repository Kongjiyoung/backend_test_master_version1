package kr.co.polycube.backendtest.backendtestmaster.winner;

import jakarta.persistence.*;
import kr.co.polycube.backendtest.backendtestmaster.lottos.Lottos;
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
    private Lottos lottos;

    private Integer rank;

    @Builder
    public Winner(Long id, Lottos lottos, Integer rank) {
        this.id = id;
        this.lottos = lottos;
        this.rank = rank;
    }
}
