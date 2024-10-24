package uz.ilmnajot.organisation_task.payload.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.organisation_task.entity.Organisation;

@Setter
@Getter
public class RegionRequest {

    private String name;

}
