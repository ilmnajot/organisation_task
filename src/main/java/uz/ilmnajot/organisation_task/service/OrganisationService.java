package uz.ilmnajot.organisation_task.service;

import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.CompanyRequest;
import uz.ilmnajot.organisation_task.payload.request.OrganisationRequest;
import uz.ilmnajot.organisation_task.payload.request.RegionRequest;

public interface OrganisationService {
    ApiResponse addOrganisation(OrganisationRequest request);

    ApiResponse updateOrganisation(Long organisationId, RegionRequest request);

    ApiResponse getOrganisation(Long organisationId);

    ApiResponse getOrganisations(int page, int size);

    ApiResponse deleteOrgansation(Long organisationId);
}
