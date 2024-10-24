package uz.ilmnajot.organisation_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.EmployeeRequest;
import uz.ilmnajot.organisation_task.service.EmployeeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public HttpEntity<ApiResponse> addEmployee(@RequestBody EmployeeRequest request) {
        ApiResponse apiResponse = employeeService.addEmployee(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public HttpEntity<ApiResponse> updateEmployee(@PathVariable("employeeId") Long employeeId,
                                                  @RequestBody EmployeeRequest request) {
        ApiResponse apiResponse = employeeService.updateEmployeeData(employeeId, request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getEmployee/{employeeId}")
    public HttpEntity<ApiResponse> getEmployee(@PathVariable("employeeId") Long employeeId) {
        ApiResponse apiResponse = employeeService.getEmployee(employeeId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/getEmployees")
    public HttpEntity<ApiResponse> getEmployees(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        ApiResponse allEmployees = employeeService.findAllEmployees(page, size);
        return ResponseEntity.status(allEmployees.isSuccess() ? 200 : 404).body(allEmployees);
    }

    @GetMapping("/getAllActiveEmployees")
    public HttpEntity<ApiResponse> getAllActiveEmployees(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        ApiResponse allEmployees = employeeService.findAllActiveEmployees(page, size);
        return ResponseEntity.status(allEmployees.isSuccess() ? 200 : 404).body(allEmployees);
    }

    @GetMapping("/getAllDeletedEmployees")
    public HttpEntity<ApiResponse> getAllDeletedEmployees(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        ApiResponse allEmployees = employeeService.getAllDeletedEmployees(page, size);
        return ResponseEntity.status(allEmployees.isSuccess() ? 200 : 404).body(allEmployees);
    }

    @DeleteMapping("/deleteEmployee/{employeeId}")
    public HttpEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        ApiResponse apiResponse = employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


}
