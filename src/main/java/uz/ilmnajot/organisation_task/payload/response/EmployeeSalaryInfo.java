package uz.ilmnajot.organisation_task.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryInfo {

    private Long organisationCount;
    private BigDecimal allSalary;
    private String pinfl;
}
