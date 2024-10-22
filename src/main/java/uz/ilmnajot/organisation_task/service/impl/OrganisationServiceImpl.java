package uz.ilmnajot.organisation_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.entity.Region;
import uz.ilmnajot.organisation_task.exception.NotFoundException;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.CompanyRequest;
import uz.ilmnajot.organisation_task.payload.request.OrganisationRequest;
import uz.ilmnajot.organisation_task.payload.request.RegionRequest;
import uz.ilmnajot.organisation_task.payload.response.OrganisationResponse;
import uz.ilmnajot.organisation_task.repository.OrganisationRepository;
import uz.ilmnajot.organisation_task.repository.RegionRepository;
import uz.ilmnajot.organisation_task.service.OrganisationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrganisationServiceImpl implements OrganisationService {

    private final OrganisationRepository organisationRepository;
    private final RegionRepository regionRepository;


    @Override
    public ApiResponse addOrganisation(OrganisationRequest request) {
        Region region = regionRepository.findById(request.getRegionId()).orElseThrow(()
                -> new NotFoundException("region not found"));
        Organisation parentOrgan = organisationRepository.findById(request.getParentOrganisationId()).orElseThrow(()
                -> new NotFoundException("organisation not found"));
        Organisation branch = new Organisation();
        branch.setName(request.getName());
        branch.setRegion(region);
        branch.setParentOrganisation(parentOrgan);
        Organisation savedOrgan = organisationRepository.save(branch);
        OrganisationResponse response = OrganisationResponse.toOrganisationResponse(savedOrgan);
        return new ApiResponse(true, "success", response);
    }

    @Override
    public ApiResponse updateOrganisation(Long organisationId, RegionRequest request) {
        Organisation organisation = organisationRepository.findById(organisationId).orElseThrow(()
                -> new NotFoundException("organisation not found"));
        organisation.setName(request.getName());
        Organisation updatedOrganisation = organisationRepository.save(organisation);
        OrganisationResponse.toOrganisationResponse(updatedOrganisation);
        return new ApiResponse(true, "success", "updated successfully");
    }

    @Override
    public ApiResponse getOrganisation(Long organisationId) {
        Organisation organisationById = getOrganisationById(organisationId);
        OrganisationResponse response = OrganisationResponse.toOrganisationResponse(organisationById);
        return new ApiResponse(true, "success", response);
    }

    @Override
    public ApiResponse getOrganisations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Organisation> organisations = organisationRepository.findAll(pageable);
        List<OrganisationResponse> responseList = organisations
                .stream()
                .map(OrganisationResponse::toOrganisationResponse)
                .toList();
        return new ApiResponse(true, "success", responseList);
    }

    @Override
    public ApiResponse deleteOrgansation(Long organisationId) {
        getOrganisationById(organisationId);
        organisationRepository.deleteById(organisationId);
        return new ApiResponse(true, "success", "Organisation has been successfully deleted");
    }

    public Organisation getOrganisationById(Long organisationId) {
        return organisationRepository.findById(organisationId).orElseThrow(() -> new NotFoundException("not found"));
    }
}
