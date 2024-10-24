package uz.ilmnajot.organisation_task.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InfoEmployeeSalary {

    private String firstName;
    private String lastName;
    private String pinfl;
    private Long organisationId;
    private double vocation;
    private double salary;
}
