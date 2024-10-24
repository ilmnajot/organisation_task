package uz.ilmnajot.organisation_task.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.organisation_task.enums.CalculationType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
public class CalculationResponse {

    private Long id;
    private BigDecimal amount;
    private Double rate;
    private LocalDate date;
    private CalculationType calculationType;
    private Long employeeId;
    private Long organisationId;
    private boolean deleted;
}
