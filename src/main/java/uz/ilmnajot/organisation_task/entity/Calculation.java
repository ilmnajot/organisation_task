package uz.ilmnajot.organisation_task.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.organisation_task.enums.CalculationType;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "calculations")
@Builder
@ToString
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String rate; //experience
    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private CalculationType calculationType;

    @ManyToOne
    private Employee employee;


}