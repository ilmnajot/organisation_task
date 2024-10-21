package uz.ilmnajot.organisation_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.CompanyRequest;
import uz.ilmnajot.organisation_task.repository.OrganisationRepository;
import uz.ilmnajot.organisation_task.service.OrganisationService;

@RequiredArgsConstructor
@Service
public class OrganisationServiceImpl implements OrganisationService {

    private final OrganisationRepository organisationRepository;

    @Override
    public ApiResponse addOrganisation(CompanyRequest request) {
        Organisation organisation = new Organisation();
        organisation.setName(request.getName());
        organisation.setRegions(request.getRegions());
        return null;
    }
}
