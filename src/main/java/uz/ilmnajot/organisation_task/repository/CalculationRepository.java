package uz.ilmnajot.organisation_task.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.organisation_task.entity.Calculation;
import uz.ilmnajot.organisation_task.payload.response.InfoEmployeeSalary;
import uz.ilmnajot.organisation_task.payload.response.InfoOfRate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {

    Optional<Calculation> findByIdAndDeletedFalse(Long id);
    Page<Calculation> findAllByDeletedIsTrue(Pageable pageable);
    Page<Calculation> findAllByDeletedIsFalse(Pageable pageable);

    @Query("select new uz.ilmnajot.organisation_task.payload.response.InfoEmployeeSalary(c.employee.firstName, c.employee.firstName, c.employee.pinfl, c.employee.organisation.id, " +
            "cast(sum(case when c.calculationType=uz.ilmnajot.organisation_task.enums.CalculationType.VACATION then c.amount else 0 end) as double)," +
            "cast(sum(case when c.calculationType = uz.ilmnajot.organisation_task.enums.CalculationType.SALARY then c.amount else 0 end) as double)) from calculations as c  " +
            "where to_char(c.date, 'YYYY.MM') = :month and c.calculationType in (uz.ilmnajot.organisation_task.enums.CalculationType.SALARY, uz.ilmnajot.organisation_task.enums.CalculationType.VACATION)" +
            " and c.deleted = false and c.employee.deleted = false  group by c.employee")
    List<InfoEmployeeSalary> getInfoAboutEmployeeSalaryAndVocation(@Param("month") String month);


    @Query("select new uz.ilmnajot.organisation_task.payload.response.InfoOfRate(sum(c.rate), e.pinfl) from calculations as c" +
            " join employees as e on c.employee.pinfl = e.pinfl where c.rate > :rate and to_char(c.date, 'YYYY.MM') = :month group by e.pinfl")
    List<InfoOfRate> getRate(Double rate, String month);
}
