package uz.ilmnajot.organisation_task.payload.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.organisation_task.entity.Employee;

import java.time.LocalDate;

@Setter
@Getter
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String pinfl;
    private LocalDate hiredDate;
    private boolean deleted;
    private Long organisationId;

    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setPinfl(employee.getPinfl());
        response.setHiredDate(employee.getHiredDate());
        response.setOrganisationId(employee.getOrganisation().getId());
        return response;
    }

    public static EmployeeResponse toActiveEmployeeResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setPinfl(employee.getPinfl());
        response.setHiredDate(employee.getHiredDate());
        response.setDeleted(employee.isDeleted());
        response.setOrganisationId(employee.getOrganisation().getId());
        return response;
    }

}
