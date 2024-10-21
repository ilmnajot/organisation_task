package uz.ilmnajot.organisation_task.payload.request;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.organisation_task.entity.Organisation;

import java.time.LocalDate;

@Setter
@Getter
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String pinfl;
    private LocalDate hiredDate;
    private boolean delete;
    private Long organisationId;


}
