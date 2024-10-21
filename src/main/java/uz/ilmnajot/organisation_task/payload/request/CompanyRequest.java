package uz.ilmnajot.organisation_task.payload.request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.organisation_task.entity.Employee;
import uz.ilmnajot.organisation_task.entity.Region;

import java.util.List;

@Setter
@Getter
public class CompanyRequest {

    private Long id;

    private String name;

    private List<RegionRequest> regions;

    private List<EmployeeRequest> employees;
}
