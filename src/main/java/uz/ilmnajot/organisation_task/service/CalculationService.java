package uz.ilmnajot.organisation_task.service;

import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.CalculateRequest;

public interface CalculationService {
    ApiResponse create(CalculateRequest request);

    ApiResponse update(CalculateRequest request, Long calculationId);

    ApiResponse getById(Long id);

    ApiResponse getAll(int page, int size);

    ApiResponse delete(Long id);


    ApiResponse getInformationWithSalary(String month);

    ApiResponse getAllDeletedCalculations(int page, int size);

    ApiResponse getInformationWithRate(Double rate,String month);
}
