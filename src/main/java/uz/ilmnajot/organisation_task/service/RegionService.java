package uz.ilmnajot.organisation_task.service;

import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.RegionRequest;

public interface RegionService {
    ApiResponse addRegion(RegionRequest request);

    ApiResponse updateRegion(Long regionId, RegionRequest request);

    ApiResponse getRegion(Long regionId);

    ApiResponse getRegions(int page, int size);

    ApiResponse deteleRegion(Long regionId);
}
