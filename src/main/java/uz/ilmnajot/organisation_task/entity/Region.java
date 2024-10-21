package uz.ilmnajot.organisation_task.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "regions")
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    public Region(String regionName) {
        this.name=regionName;
    }
}
