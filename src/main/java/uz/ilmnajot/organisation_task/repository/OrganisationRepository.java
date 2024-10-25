package uz.ilmnajot.organisation_task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.response.EmployeeSalaryInfo;
import uz.ilmnajot.organisation_task.payload.response.InfoEmployeeOrganization;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    Optional<Organisation> findByIdAndDeletedFalse(Long id);
    Page<Organisation> findAllByDeletedIsFalse(Pageable pageable);
    Page<Organisation> findAllByDeletedIsTrue(Pageable pageable);

    boolean existsByNameAndRegionId(String name, Long id);


    ApiResponse getGeneralRate(Double rate, String month);
    @Query("select new uz.ilmnajot.organisation_task.payload.response.InfoEmployeeOrganization(e.firstName, e.lastName, e.pinfl,avg(c.amount), o.name, e.id, o.id, o.region.id) from organisations as o " +
            "join organisations as parent_id on o.parentOrganisation.id= parent_id.id " +
            "join employees as e on e.organisation.id=o.id " +
            "join calculations as c on c.employee.id = e.id where to_char(c.date, 'YYYY.MM')=:month and parent_id.id =:id")
    List<InfoEmployeeOrganization> getInfoAboutEmployeeOrgan(@Param("month") String month, @Param("id") Long id);


    @Query("select new uz.ilmnajot.organisation_task.payload.response.EmployeeSalaryInfo(count(e.organisation.id), sum (c.amount), e.pinfl) " +
            "from calculations as c " +
            "join employees as e on c.employee.id = e.id " +
            "join organisations  as o  on e.organisation.id = o.id " +
            "where to_char(c.date, 'YYYY.MM') = :month group by o.region.id,e.pinfl")
    List<EmployeeSalaryInfo> getEmployeeSalaryInfo(String month);


}

