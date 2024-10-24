package uz.ilmnajot.organisation_task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "organisations")
@Builder
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    public Organisation parentOrganisation;

//    public Long getPatentId() {
//        if (parentOrganisation != null) {
//            return parentOrganisation.getPatentId();
//        }
//        return null;
//    }

}
