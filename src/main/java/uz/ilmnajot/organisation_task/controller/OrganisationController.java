package uz.ilmnajot.organisation_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.CompanyRequest;
import uz.ilmnajot.organisation_task.payload.request.OrganisationRequest;
import uz.ilmnajot.organisation_task.payload.request.RegionRequest;
import uz.ilmnajot.organisation_task.service.OrganisationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    private final OrganisationService organisationService;


    @PostMapping("/addOrganisation")
    public HttpEntity<ApiResponse> addOrganisation(@RequestBody OrganisationRequest request) {
        ApiResponse apiResponse = organisationService.addOrganisation(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/updateOrganisation/{organisationId}")
    public HttpEntity<ApiResponse> updateOrganisation(@PathVariable("organisationId") Long organisationId,
                                                      @RequestBody RegionRequest request) {
        ApiResponse apiResponse = organisationService.updateOrganisation(organisationId, request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getOrganisation/{organisationId}")
    public HttpEntity<ApiResponse> getOrganisation(@PathVariable("organisationId") Long organisationId) {
        ApiResponse apiResponse = organisationService.getOrganisation(organisationId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getOrganisations")
    public HttpEntity<ApiResponse> getOrganisations(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        ApiResponse apiResponse = organisationService.getOrganisations(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/deleteOrganisation/{organisationId}")
    public HttpEntity<ApiResponse> deleteOrganisation(@PathVariable(name = "organisationId") Long organisationId) {
        ApiResponse apiResponse = organisationService.deleteOrgansation(organisationId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);

    }


}
