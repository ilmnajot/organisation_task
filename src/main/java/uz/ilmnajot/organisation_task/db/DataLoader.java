package uz.ilmnajot.organisation_task.db;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.ilmnajot.organisation_task.entity.Employee;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.entity.Region;
import uz.ilmnajot.organisation_task.repository.EmployeeRepository;
import uz.ilmnajot.organisation_task.repository.OrganisationRepository;
import uz.ilmnajot.organisation_task.repository.RegionRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {


    private final OrganisationRepository organisationRepository;
    private final RegionRepository regionRepository;
    private final EmployeeRepository employeeRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")){

            Region region = new Region();
            region.setName("Samarkand");

            Organisation organisation = new Organisation();
            organisation.setName("IlmNajot");
            organisation.setRegion(region);
            organisation.setParentOrganisation(organisation);

            Employee employee = new Employee();
            employee.setFirstName("Elbek");
            employee.setLastName("Umaror");
            employee.setHiredDate(LocalDate.parse("2024-10-10"));
            employee.setFiredDate(null);
            employee.setOrganisation(organisation);

            regionRepository.save(region);
            organisationRepository.save(organisation);
            employeeRepository.save(employee);



        }
    }
}
