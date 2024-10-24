package uz.ilmnajot.organisation_task.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.organisation_task.entity.Region;

@Setter
@Getter
@Builder
public class RegionResponse {


    private Long id;
    private String name;

//    public static RegionResponse toRegionResponse(Region region){
//        RegionResponse response = new RegionResponse();
//        response.setId(region.getId());
//        response.setName(region.getName());
//        return response;
//    }

}
