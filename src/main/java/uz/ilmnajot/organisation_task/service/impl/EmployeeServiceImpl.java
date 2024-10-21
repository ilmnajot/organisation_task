package uz.ilmnajot.organisation_task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.ilmnajot.organisation_task.entity.Employee;
import uz.ilmnajot.organisation_task.entity.Organisation;
import uz.ilmnajot.organisation_task.exception.AlreadyExistFoundException;
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
        Optional<Employee> optionalEmployee = employeeRepository.findByPinfl(request.getPinfl());

        if (optionalEmployee.isPresent()) {
            throw new AlreadyExistFoundException("This employee already exists with pnfl " + request.getPinfl());
        }
        Organisation organisation = organisationRepository.findById(request.getOrganisationId()).orElseThrow(()
                -> new NotFoundException("No organization found with id " + request.getOrganisationId()));

        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPinfl(request.getPinfl());
        employee.setHiredDate(request.getHiredDate());
        employee.setOrganisation(organisation);
        employeeRepository.save(employee);
        return new ApiResponse(true, "Employee added successfully");
    }

    @Override
    public ApiResponse getEmployee(Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        EmployeeResponse employeeResponse = EmployeeResponse.toEmployeeResponse(employee);
        return new ApiResponse(true, employeeResponse);
    }

    @Override
    public ApiResponse findAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeeList = employeeRepository.findAll(pageable);
        List<EmployeeResponse> responseList = employeeList
                .stream()
                .map(EmployeeResponse::toActiveEmployeeResponse)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("employees", responseList);
        response.put("currentPage", employeeList.getNumber());
        response.put("totalPages", employeeList.getTotalPages());
        response.put("totalElements", employeeList.getTotalElements());
        response.put("size", employeeList.getSize());
        return new ApiResponse(true, "success", response);
    }

    @Override
    public ApiResponse updateEmployeeData(Long employeeId, EmployeeRequest request) {
        Optional<Employee> optionalEmployee = employeeRepository.findByPinflOrId(request.getPinfl(), employeeId);
        Organisation organisation = organisationRepository.findById(request.getOrganisationId()).orElseThrow(()
                -> new NotFoundException("organisation is not found with id: " + request.getOrganisationId()));
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setFirstName(request.getFirstName());
            employee.setLastName(request.getLastName());
            employee.setPinfl(request.getPinfl());
            employee.setHiredDate(request.getHiredDate());
            employee.setOrganisation(organisation);
            Employee saved = employeeRepository.save(employee);
            EmployeeResponse employeeResponse = EmployeeResponse.toEmployeeResponse(saved);
            return new ApiResponse(true, "success", employeeResponse);
        }
        throw new NotFoundException("employee not found");
    }

    @Override
    public ApiResponse deleteEmployee(Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        employee.setDeleted(true);
        employeeRepository.save(employee);
        return new ApiResponse(true, "success", "employee has been deleted");
    }

    @Override
    public ApiResponse findAllActiveEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAllByDeletedFalse(pageable);
        if (employees.isEmpty()) {
            throw new NotFoundException("employees are not found");
        }
        List<EmployeeResponse> responseList = employees
                .stream()
                .map(EmployeeResponse::toActiveEmployeeResponse)
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
        Page<Employee> employeePage = employeeRepository.findAllByDeletedTrue(pageable);
        if (employeePage.isEmpty()) {
            throw new NotFoundException("employees are not found");
        }
        List<EmployeeResponse> responseList = employeePage
                .stream()
                .map(EmployeeResponse::toActiveEmployeeResponse)
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("employees", responseList);
        response.put("currentPage", employeePage.getNumber());
        response.put("totalPages", employeePage.getTotalPages());
        response.put("totalElements", employeePage.getTotalElements());
        response.put("size", employeePage.getSize());
        return new ApiResponse(true, "success", response);
    }

    private Employee getEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        throw new NotFoundException("No employee found with id " + employeeId);
    }
}
