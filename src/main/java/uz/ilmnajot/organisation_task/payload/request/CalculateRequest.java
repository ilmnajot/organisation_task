package uz.ilmnajot.organisation_task.payload.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.organisation_task.entity.Employee;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.enums.CalculationType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
public class CalculateRequest {


    private BigDecimal amount;
    private Double rate;
    private LocalDate date;
    private CalculationType calculationType;
    private Long employeeId;
    private Long organisationId;
    private boolean deleted;
}
