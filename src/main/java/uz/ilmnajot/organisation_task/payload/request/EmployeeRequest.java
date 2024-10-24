package uz.ilmnajot.organisation_task.payload.request;
import lombok.Getter;
import lombok.Setter;
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
