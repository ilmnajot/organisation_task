package uz.ilmnajot.organisation_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ilmnajot.organisation_task.entity.Region;
import uz.ilmnajot.organisation_task.exception.AlreadyExistFoundException;
import uz.ilmnajot.organisation_task.exception.NotFoundException;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.RegionRequest;
import uz.ilmnajot.organisation_task.payload.response.RegionResponse;
import uz.ilmnajot.organisation_task.repository.RegionRepository;
import uz.ilmnajot.organisation_task.service.RegionService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public ApiResponse addRegion(RegionRequest request) {
        Optional<Region> optionalRegion = regionRepository.findByName(request.getName());
        if (optionalRegion.isPresent()) {
            throw new AlreadyExistFoundException("region with name " + request.getName() + " is already exists");
        }
        Region region = new Region();
        region.setName(request.getName());
        Region savedRegion = regionRepository.save(region);
        return new ApiResponse(true, "success", savedRegion);
    }

    @Override
    public ApiResponse updateRegion(Long regionId, RegionRequest request) {
        Region region = getRegionById(regionId);
        region.setName(request.getName());
        Region updatedRegion = regionRepository.save(region);
        return new ApiResponse(true, "success", updatedRegion);
    }

    @Override
    public ApiResponse getRegion(Long regionId) {
        Region region = getRegionById(regionId);
        return new ApiResponse(true, "success", region);
    }

    @Override
    public ApiResponse getRegions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Region> regionPage = regionRepository.findAll(pageable);
        if (regionPage.isEmpty()){
            throw new NotFoundException("not regions found");
        }
        List<RegionResponse> requestList = regionPage
                .stream()
                .map(RegionResponse::toRegionResponse)
                .toList();
        return new ApiResponse(true, "success", requestList);
    }

    @Override
    public ApiResponse deteleRegion(Long regionId) {
        getRegionById(regionId);
        regionRepository.deleteById(regionId);
        return new ApiResponse(true, "success", "the region with id " + regionId + " has been deleted");
    }


    public Region getRegionById(Long regionId) {
        Optional<Region> optionalRegion = regionRepository.findById(regionId);
        if (optionalRegion.isPresent()) {
            return optionalRegion.get();
        }
        throw new NotFoundException("not found region");
    }

}
