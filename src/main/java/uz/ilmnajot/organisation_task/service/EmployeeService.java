package uz.ilmnajot.organisation_task.service;

import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.EmployeeRequest;

public interface EmployeeService {

    ApiResponse addEmployee(EmployeeRequest request);

    ApiResponse getEmployee(Long employeeId);

    ApiResponse findAllEmployees(int page, int size);

    ApiResponse updateEmployeeData(Long employeeId, EmployeeRequest request);

    ApiResponse deleteEmployee(Long employeeId);

    ApiResponse findAllActiveEmployees(int page, int size);

    ApiResponse getAllDeletedEmployees(int page, int size);
}
