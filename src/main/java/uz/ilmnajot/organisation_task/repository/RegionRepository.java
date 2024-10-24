package uz.ilmnajot.organisation_task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.organisation_task.entity.Region;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findByName(String name);

    boolean existsByName(String name);

    Page<Region> findAllByDeletedFalse(Pageable pageable);

    Optional<Region> findByIdAndDeletedFalse(Long id);
}
