package uz.ilmnajot.organisation_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ilmnajot.organisation_task.entity.Employee;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.exception.NotFoundException;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.EmployeeRequest;
import uz.ilmnajot.organisation_task.payload.response.EmployeeResponse;
import uz.ilmnajot.organisation_task.repository.EmployeeRepository;
import uz.ilmnajot.organisation_task.repository.OrganisationRepository;
import uz.ilmnajot.organisation_task.service.EmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OrganisationRepository organisationRepository;


    @Override
    public ApiResponse addEmployee(EmployeeRequest request) {
        Employee employee = toEmployeeEntity(request);
        Employee addedEmployee = employeeRepository.save(employee);
        EmployeeResponse employeeResponse = toEmployeeResponse(addedEmployee);
        return new ApiResponse(true, "new employee has been added successfully", employeeResponse);
    }

    @Override
    public ApiResponse updateEmployeeData(Long employeeId, EmployeeRequest request) {
        Employee employee = employeeRepository.findByIdAndDeletedFalse(employeeId).orElseThrow(()
                -> new NotFoundException("not found employee"));
        Employee updatedEmployee = toUpdateEmployee(request, employee);
        Employee saved = employeeRepository.save(updatedEmployee);
        return new ApiResponse(true, "success", saved);

    }

    @Override
    public ApiResponse getEmployee(Long employeeId) {
        Employee employee = employeeRepository.findByIdAndDeletedFalse(employeeId).orElseThrow(()
                -> new NotFoundException("employee not found"));
        EmployeeResponse employeeResponse = toEmployeeResponse(employee);
        return new ApiResponse(true, employeeResponse);
    }

    @Override
    public ApiResponse findAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<EmployeeResponse> employeeList = employees
                .stream()
                .map(this::toEmployeeResponse)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("employees", employeeList);
        response.put("currentPage", employees.getNumber());
        response.put("totalPages", employees.getTotalPages());
        response.put("totalElements", employees.getTotalElements());
        response.put("size", employees.getSize());
        return new ApiResponse(true, "success", response);
    }

    @Override
    public ApiResponse deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findByIdAndDeletedFalse(employeeId).orElseThrow(()
                -> new NotFoundException("employee not found"));
        employee.setDeleted(true);
        Employee deletedEmployee = employeeRepository.save(employee);
        EmployeeResponse employeeResponse = toEmployeeResponse(deletedEmployee);
        return new ApiResponse(true, employeeResponse);
    }

    @Override
    public ApiResponse findAllActiveEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAllByDeletedIsFalse(pageable);
        if (employees.isEmpty()) {
            throw new NotFoundException("employees are not found");
        }
        List<EmployeeResponse> responseList = employees
                .stream()
                .map(this::toEmployeeResponse)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("employees", responseList);
        response.put("currentPage", employees.getNumber());
        response.put("totalPages", employees.getTotalPages());
        response.put("totalElements", employees.getTotalElements());
        response.put("size", employees.getSize());
        return new ApiResponse(true, "success", response);
    }

    @Override
    public ApiResponse getAllDeletedEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findAllByDeletedIsTrue(pageable);
        if (employeePage.isEmpty()) {
            throw new NotFoundException("employees are not found");
        }
        List<EmployeeResponse> responseList = employeePage
                .stream()
                .map(this::toEmployeeResponse)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("employees", responseList);
        response.put("currentPage", employeePage.getNumber());
        response.put("totalPages", employeePage.getTotalPages());
        response.put("totalElements", employeePage.getTotalElements());
        response.put("size", employeePage.getSize());
        return new ApiResponse(true, "success", response);
    }

    private Employee toEmployeeEntity(EmployeeRequest request) {
        Organisation organisation = organisationRepository.findByIdAndDeletedFalse(request.getOrganisationId()).orElseThrow(()
                -> new NotFoundException("Organisation not found"));
        return Employee
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .pinfl(request.getPinfl())
                .hiredDate(request.getHiredDate())
                .organisation(organisation)
                .build();
    }

    private EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse
                .builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .pinfl(employee.getPinfl())
                .hiredDate(employee.getHiredDate())
                .organisationId(employee.getOrganisation().getId())
                .deleted(employee.isDeleted())
                .build();
    }

    private Employee toUpdateEmployee(EmployeeRequest updateRequest, Employee employee) {
        Organisation organisation = organisationRepository.findByIdAndDeletedFalse(updateRequest.getOrganisationId()).orElseThrow(()
                -> new NotFoundException("organisation not found "));
        if (updateRequest.getFirstName() != null) {
            employee.setFirstName(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName() != null) {
            employee.setLastName(updateRequest.getLastName());
        }
        if (updateRequest.getPinfl() != null) {
            employee.setPinfl(updateRequest.getPinfl());
        }
        if (updateRequest.getHiredDate() != null) {
            employee.setHiredDate(updateRequest.getHiredDate());
        }
        if (updateRequest.getOrganisationId() != null) {
            employee.setOrganisation(organisation);
        }

        return employee;

    }
}
