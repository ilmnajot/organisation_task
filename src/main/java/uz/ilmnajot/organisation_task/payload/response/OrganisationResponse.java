package uz.ilmnajot.organisation_task.payload.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.organisation_task.entity.Organisation;

@Setter
@Getter
public class OrganisationResponse {

    private Long id;

    private String name;

    private Long regionId;

    public Long parentOrganisationId;

    public static OrganisationResponse toOrganisationResponse(Organisation organisation){
        OrganisationResponse response = new OrganisationResponse();
        response.setId(organisation.getId());
        response.setName(organisation.getName());
        response.setRegionId(organisation.getRegion().getId());
        response.setParentOrganisationId(organisation.getParentOrganisation().getId());
        return response;
    }

    public OrganisationResponse() {

    }
}
