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
       existsByName(request.getName());
        Region regionEntity = toRegionEntity(request);
        Region savedRegion = regionRepository.save(regionEntity);
        RegionResponse regionResponse = toRegionResponse(savedRegion);
        return new ApiResponse(true, "success", regionResponse);
    }

    @Override
    public ApiResponse updateRegion(Long regionId, RegionRequest request) {
       existsByName(request.getName());
        Region region = regionRepository.findById(regionId).orElseThrow(()
                -> new NotFoundException("region not found"));
        Region converted = fromUpdateRegion(request, region);
        Region updatedRegion = regionRepository.save(converted);
        return new ApiResponse(true, "success", updatedRegion);
    }

    @Override
    public ApiResponse getRegion(Long regionId) {
        Region region = getRegionById(regionId);
        RegionResponse regionResponse = toRegionResponse(region);
        return new ApiResponse(true, "success", regionResponse);
    }

    @Override
    public ApiResponse getRegions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Region> regionPage = regionRepository.findAllByDeletedFalse(pageable);
        if (regionPage.isEmpty()) {
            throw new NotFoundException("not regions found");
        }
        List<RegionResponse> requestList = regionPage
                .stream()
                .map(this::toRegionResponse)
                .toList();
        return new ApiResponse(true, "success", requestList);
    }

    @Override
    public ApiResponse deleteRegion(Long regionId) {
        Region region = regionRepository.findByIdAndDeletedFalse(regionId).orElseThrow(()
                -> new NotFoundException("region not found"));
        region.setDeleted(true);
        Region savedRegion = regionRepository.save(region);
        RegionResponse regionResponse = toRegionResponse(savedRegion);
        return new ApiResponse(true, "success", "the region has been deleted: " + regionResponse);
    }


    public Region getRegionById(Long regionId) {
        Optional<Region> optionalRegion = regionRepository.findById(regionId);
        if (optionalRegion.isPresent()) {
            return optionalRegion.get();
        }
        throw new NotFoundException("not found region");
    }

    private RegionResponse toRegionResponse(Region region) {
        return RegionResponse
                .builder()
                .id(region.getId())
                .name(region.getName())
                .build();
    }

    private Region toRegionEntity(RegionRequest request) {
        return Region.builder()
                .name(request.getName())
                .build();
    }

    private void existsByName(String name){
        if (regionRepository.existsByName(name)) {
            throw new AlreadyExistFoundException("This name already has been taken");
        }
    }

    private Region fromUpdateRegion(RegionRequest request, Region region){
        if (request.getName()!=null){
            region.setName(request.getName());
        }
        return region;
    }

}
