package uz.ilmnajot.organisation_task.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.RegionRequest;
import uz.ilmnajot.organisation_task.service.RegionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/regions")
public class RegionController {

    private final RegionService regionService;

    @PostMapping("/addRegion")
    public HttpEntity<ApiResponse> addRegion(@RequestBody RegionRequest request) {
        ApiResponse apiResponse = regionService.addRegion(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/updateRegion/{regionId}")
    public HttpEntity<ApiResponse> updateRegion(@PathVariable("regionId") Long regionId,
                                                @RequestBody RegionRequest request) {
        ApiResponse apiResponse = regionService.updateRegion(regionId, request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getRegion/{regionId}")
    public HttpEntity<ApiResponse> getRegion(@PathVariable("regionId") Long regionId) {
        ApiResponse apiResponse = regionService.getRegion(regionId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getRegions")
    public HttpEntity<ApiResponse> getRegions(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size) {
        ApiResponse apiResponse = regionService.getRegions(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/deleteRegion/{regionId}")
    public HttpEntity<ApiResponse> deleteRegion(@PathVariable(name = "regionId") Long regionId) {
        ApiResponse apiResponse = regionService.deteleRegion(regionId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);

    }


}
