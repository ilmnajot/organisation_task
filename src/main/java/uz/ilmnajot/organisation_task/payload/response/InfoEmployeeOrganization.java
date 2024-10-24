package uz.ilmnajot.organisation_task.payload.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InfoEmployeeOrganization {

    private String firstName;
    private String lastName;
    private String pinfl;
    private BigDecimal averageSalary;
    private String organisationName;
    private Long employeeId;
    private Long organisationId;
    private Long regionId;

    public InfoEmployeeOrganization(String firstName, String lastName, String pinfl, BigDecimal averageSalary, String organisationName, Long employeeId, Long regionId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.pinfl = pinfl;
        this.averageSalary = averageSalary;
        this.organisationName = organisationName;
        this.employeeId = employeeId;
        this.regionId = regionId;
    }

}
