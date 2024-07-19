package kr.co.polycube.backendtest.backendtestmaster.lottos;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "lottos_tb")
@Entity
public class Lottos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number1;
    private Integer number2;
    private Integer number3;
    private Integer number4;
    private Integer number5;
    private Integer number6;

    @Builder
    public Lottos(Long id, Integer number1, Integer number2, Integer number3, Integer number4, Integer number5, Integer number6) {
        this.id = id;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
        this.number6 = number6;
    }
}
