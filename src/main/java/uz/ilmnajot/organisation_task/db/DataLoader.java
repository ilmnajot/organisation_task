package uz.ilmnajot.organisation_task.db;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.entity.Region;
import uz.ilmnajot.organisation_task.repository.OrganisationRepository;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {


    private final OrganisationRepository organisationRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")){

            List<Region> regionList = Arrays.asList(
                    new Region("Tashkent"),
                    new Region("Samarkand"),
                    new Region("Nukust")
            );

            Organisation organisation = new Organisation();
            organisation.setName("CompanyName");
            organisation.setRegions(regionList);
            organisation.setEmployees(null);
            organisationRepository.save(organisation);

        }
    }
}
