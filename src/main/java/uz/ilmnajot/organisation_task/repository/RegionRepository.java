package uz.ilmnajot.organisation_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.organisation_task.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
