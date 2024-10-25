package uz.ilmnajot.organisation_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.entity.Region;
import uz.ilmnajot.organisation_task.exception.AlreadyExistFoundException;
import uz.ilmnajot.organisation_task.exception.NotFoundException;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.OrganisationRequest;
import uz.ilmnajot.organisation_task.payload.response.EmployeeSalaryInfo;
import uz.ilmnajot.organisation_task.payload.response.InfoEmployeeOrganization;
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
        existByName(request.getName(), request.getRegionId());
        Organisation organisation = toOrganisationEntity(request);
        Organisation savedOrgan = organisationRepository.save(organisation);
        OrganisationResponse response = toOrganisationResponse(savedOrgan);
        return new ApiResponse(true, "success", response);
    }

    @Override
    public ApiResponse updateOrganisation(Long organisationId, OrganisationRequest request) {
        existByName(request.getName(), request.getRegionId());
        Organisation organisation = organisationRepository.findByIdAndDeletedFalse(organisationId).orElseThrow(()
                -> new NotFoundException("organisation not found"));
        Organisation organ = fromUpdateToOrganisation(request, organisation);
        Organisation addedOrgan = organisationRepository.save(organ);
        OrganisationResponse response = toOrganisationResponse(addedOrgan);
        return new ApiResponse(true, "success", response);
    }

    @Override
    public ApiResponse getOrganisation(Long organisationId) {
        Organisation organisation = organisationRepository.findByIdAndDeletedFalse(organisationId).orElseThrow(()
                -> new NotFoundException("organisation not found"));
        OrganisationResponse response = toOrganisationResponse(organisation);
        return new ApiResponse(true, "success", response);
    }

    @Override
    public ApiResponse getOrganisations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Organisation> organisations = organisationRepository.findAllByDeletedIsFalse(pageable);
        List<OrganisationResponse> responseList = organisations
                .stream()
                .map(this::toOrganisationResponse)
                .toList();
        return new ApiResponse(true, "success", responseList);
    }

    @Override
    public ApiResponse deleteOrganisation(Long organisationId) {
        Organisation organisation = getOrganisationById(organisationId);
        organisation.setDeleted(true);
        Organisation deletedOrgan = organisationRepository.save(organisation);
        OrganisationResponse response = toOrganisationResponse(deletedOrgan);
        return new ApiResponse(true, "success", "Organisation has been successfully deleted" + response);
    }

    @Override
    public ApiResponse getDeletedOrganisations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Organisation> organisations = organisationRepository.findAllByDeletedIsTrue(pageable);
        List<OrganisationResponse> responseList = organisations
                .stream()
                .map(this::toOrganisationResponse)
                .toList();
        return new ApiResponse(true, "success", responseList);
    }

    @Override
    public ApiResponse getInfoEmployee(String month, Long parentId) {
        List<InfoEmployeeOrganization> infoAboutEmployeeOrgan = organisationRepository.getInfoAboutEmployeeOrgan(month, parentId);
        if (infoAboutEmployeeOrgan.isEmpty()){
            throw new NotFoundException("information not found");
        }
        return new ApiResponse(true, "success", infoAboutEmployeeOrgan);
    }

    @Override
    public ApiResponse getInfoEmployeeSalary(String month) {
        List<EmployeeSalaryInfo> employeeSalaryInfo = organisationRepository.getEmployeeSalaryInfo(month);
        if (employeeSalaryInfo.isEmpty()){
            throw new NotFoundException("data not found");
        }
        return new ApiResponse(true, "success", employeeSalaryInfo);
    }


    public Organisation getOrganisationById(Long organisationId) {
        return organisationRepository.findByIdAndDeletedFalse(organisationId).orElseThrow(()
                -> new NotFoundException("organisation not found"));
    }

    private Organisation toOrganisationEntity(OrganisationRequest request) {
        Organisation parent = null;
        if (request.getParentOrganisationId() != null) {

            parent = organisationRepository.findByIdAndDeletedFalse(request.getParentOrganisationId()).orElseThrow(()
                    -> new NotFoundException("organisation not found"));
        }
        Region region = regionRepository.findByIdAndDeletedFalse(request.getRegionId()).orElseThrow(()
                -> new NotFoundException("Region not found"));
        return Organisation
                .builder()
                .name(request.getName())
                .parentOrganisation(parent)
                .region(region)
                .build();
    }

    private OrganisationResponse toOrganisationResponse(Organisation organisation) {

        return OrganisationResponse
                .builder()
                .id(organisation.getId())
                .name(organisation.getName())
                .regionId(organisation.getRegion().getId())
                .parentOrganisationId(organisation.getParentOrganisation().getId())
                .deleted(organisation.isDeleted())
                .build();
    }

    private void existByName(String name, Long regionId) {
        if (organisationRepository.existsByNameAndRegionId(name, regionId)) {
            throw new AlreadyExistFoundException("In this region this name is already taken");
        }
    }

    private Organisation fromUpdateToOrganisation(OrganisationRequest request, Organisation organisation) {

        Organisation organisation1 = organisationRepository.findByIdAndDeletedFalse(request.getParentOrganisationId()).orElseThrow(()
                -> new NotFoundException("organisation not found"));
        Region region = regionRepository.findByIdAndDeletedFalse(request.getRegionId()).orElseThrow(()
                -> new NotFoundException("region not found"));

        if (request.getName() != null) {
            organisation.setName(request.getName());
        }
        if (request.getParentOrganisationId() != null) {
            organisation.setParentOrganisation(organisation1);
        }
        if (request.getRegionId() != null) {
            organisation.setRegion(region);
        }
        return organisation;
    }

}
