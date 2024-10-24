package uz.ilmnajot.organisation_task.service;

import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.OrganisationRequest;

public interface OrganisationService {
    ApiResponse addOrganisation(OrganisationRequest request);

    ApiResponse updateOrganisation(Long organisationId, OrganisationRequest request);

    ApiResponse getOrganisation(Long organisationId);

    ApiResponse getOrganisations(int page, int size);

    ApiResponse deleteOrganisation(Long organisationId);

    ApiResponse getDeletedOrganisations(int page, int size);
}
