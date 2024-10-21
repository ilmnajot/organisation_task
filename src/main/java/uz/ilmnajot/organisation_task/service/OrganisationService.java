package uz.ilmnajot.organisation_task.service;

import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.CompanyRequest;

public interface OrganisationService {
    ApiResponse addOrganisation(CompanyRequest request);
}
