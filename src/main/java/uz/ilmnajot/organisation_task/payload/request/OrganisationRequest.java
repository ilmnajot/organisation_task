package uz.ilmnajot.organisation_task.payload.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.entity.Region;
@Setter
@Getter
@Builder
public class OrganisationRequest {

    private String name;

    private Long regionId;

    public Long parentOrganisationId;
}
