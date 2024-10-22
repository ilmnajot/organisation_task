package uz.ilmnajot.organisation_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.organisation_task.entity.Calculation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {

//    @Query("SELECT e.firstName, e.lastName, c.amount, c.calculationType FROM calculations c JOIN c.employee e where c.calculationType IN (:calculationType) AND c.date between :hiredDate and : firedDate")
//    List<Object []> findBySalaryAndVacation(@Param("hiredDate")LocalDate hiredDate, @Param("firedDate") LocalDate firedDate);

}
