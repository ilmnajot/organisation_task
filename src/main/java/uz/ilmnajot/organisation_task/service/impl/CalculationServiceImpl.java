package uz.ilmnajot.organisation_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ilmnajot.organisation_task.entity.Calculation;
import uz.ilmnajot.organisation_task.entity.Employee;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.exception.NotFoundException;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.CalculateRequest;
import uz.ilmnajot.organisation_task.payload.response.CalculationResponse;
import uz.ilmnajot.organisation_task.payload.response.InfoEmployeeSalary;
import uz.ilmnajot.organisation_task.payload.response.InfoOfRate;
import uz.ilmnajot.organisation_task.repository.CalculationRepository;
import uz.ilmnajot.organisation_task.repository.EmployeeRepository;
import uz.ilmnajot.organisation_task.repository.OrganisationRepository;
import uz.ilmnajot.organisation_task.service.CalculationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CalculationServiceImpl implements CalculationService {

    private final CalculationRepository calculationRepository;
    private final EmployeeRepository employeeRepository;
    private final OrganisationRepository organisationRepository;

    @Override
    public ApiResponse create(CalculateRequest request) {
        Calculation calculation = toCalculationEntity(request);
        Calculation save = calculationRepository.save(calculation);
        CalculationResponse calculationResponse = toCalculationResponse(save);
        return new ApiResponse(true, "success", calculationResponse);
    }

    @Override
    public ApiResponse update(CalculateRequest request, Long calculationId) {
        Calculation calculation = toCalculationEntity(request);
        calculation.setId(calculationId);
        Calculation save = calculationRepository.save(calculation);
        CalculationResponse calculationResponse = toCalculationResponse(save);
        return new ApiResponse(true, "success", calculationResponse);
    }

    @Override
    public ApiResponse getById(Long id) {
        Calculation calculation = calculationRepository.findByIdAndDeletedFalse(id).orElseThrow(()
                -> new NotFoundException("not found"));
        CalculationResponse calculationResponse = toCalculationResponse(calculation);
        return new ApiResponse(true, "success", calculationResponse);
    }

    @Override
    public ApiResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Calculation> calculations = calculationRepository.findAllByDeletedIsFalse(pageable);
        List<CalculationResponse> responseList = calculations
                .stream()
                .map(this::toCalculationResponse)
                .toList();
        return new ApiResponse(true, "success", responseList);
    }

    @Override
    public ApiResponse getAllDeletedCalculations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Calculation> calculations = calculationRepository.findAllByDeletedIsTrue(pageable);
        List<CalculationResponse> responseList = calculations
                .stream()
                .map(this::toCalculationResponse)
                .toList();
        return new ApiResponse(true, "success", responseList);
    }

    @Override
    public ApiResponse getInformationWithRate(Double rate, String month) {
        List<InfoOfRate> rateList = calculationRepository.getRate(rate, month);
        if (rateList.isEmpty()) {
            throw new NotFoundException("no content found");
        }
        return new ApiResponse(true, "success", rateList);
    }

    @Override
    public ApiResponse delete(Long id) {
        Calculation calculation = calculationRepository.findByIdAndDeletedFalse(id).orElseThrow(()
                -> new NotFoundException("calculation not found "));
        calculation.setDeleted(true);
        Calculation save = calculationRepository.save(calculation);
        CalculationResponse calculationResponse = toCalculationResponse(save);
        return new ApiResponse(true, "success", calculationResponse);
    }


    @Override
    public ApiResponse getInformationWithSalary(String month) {
        List<InfoEmployeeSalary> info = calculationRepository.getInfoAboutEmployeeSalaryAndVocation(month);
        if (info.isEmpty()) {
            throw new NotFoundException("not found any data");
        }
        return new ApiResponse(true, "success", info);
    }

    private Calculation toCalculationEntity(CalculateRequest request) {
        Organisation organisation = organisationRepository.findByIdAndDeletedFalse(request.getOrganisationId()).orElseThrow(()
                -> new NotFoundException("Organisation not found"));
        Employee employee = employeeRepository.findByIdAndDeletedFalse(request.getEmployeeId()).orElseThrow(()
                -> new NotFoundException("employee not found"));
        return Calculation
                .builder()
                .amount(request.getAmount())
                .rate(request.getRate())
                .date(request.getDate())
                .calculationType(request.getCalculationType())
                .employee(employee)
                .organisation(organisation)
                .deleted(request.isDeleted())
                .build();
    }

    private CalculationResponse toCalculationResponse(Calculation calculation) {
        Organisation organisation = organisationRepository.findByIdAndDeletedFalse(calculation.getOrganisation().getId()).orElseThrow(()
                -> new NotFoundException("Organisation not found"));
        Employee employee = employeeRepository.findByIdAndDeletedFalse(calculation.getEmployee().getId()).orElseThrow(()
                -> new NotFoundException("employee not found"));
        return CalculationResponse
                .builder()
                .id(calculation.getId())
                .amount(calculation.getAmount())
                .rate(calculation.getRate())
                .date(calculation.getDate())
                .calculationType(calculation.getCalculationType())
                .employeeId(employee.getId())
                .organisationId(organisation.getId())
                .deleted(calculation.isDeleted())
                .build();
    }

    private Calculation toCalculationResponse(CalculateRequest request, Calculation calculation) {
        Organisation organisation = organisationRepository.findByIdAndDeletedFalse(request.getOrganisationId()).orElseThrow(()
                -> new NotFoundException("Organisation not found"));
        Employee employee = employeeRepository.findByIdAndDeletedFalse(request.getEmployeeId()).orElseThrow(()
                -> new NotFoundException("employee not found"));
        if (request.getAmount() != null) {
            calculation.setAmount(request.getAmount());
        }
        if (request.getRate() != null) {
            calculation.setRate(request.getRate());
        }
        if (request.getDate() != null) {
            calculation.setDate(request.getDate());
        }
        if (request.getCalculationType() != null) {
            calculation.setCalculationType(request.getCalculationType());
        }
        if (request.getEmployeeId() != null) {
            calculation.setEmployee(employee);
        }
        if (request.getOrganisationId() != null) {
            calculation.setOrganisation(organisation);
        }
        return calculation;
    }


}
