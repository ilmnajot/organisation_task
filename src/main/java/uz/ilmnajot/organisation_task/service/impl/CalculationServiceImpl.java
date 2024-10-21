package uz.ilmnajot.organisation_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ilmnajot.organisation_task.repository.CalculationRepository;
import uz.ilmnajot.organisation_task.service.CalculationService;

@RequiredArgsConstructor
@Service
public class CalculationServiceImpl implements CalculationService {

    private final CalculationRepository calculationRepository;

}
