package uz.ilmnajot.organisation_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.CompanyRequest;
import uz.ilmnajot.organisation_task.service.OrganisationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/organisations")
public class OrganisationController {

    private final OrganisationService organisationService;


    @PostMapping("/addOrganisation")
    public HttpEntity<ApiResponse> addOrganisation(@RequestBody CompanyRequest request){
        organisationService.addOrganisation(request);
    }





}
