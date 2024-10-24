package uz.ilmnajot.organisation_task.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.organisation_task.payload.common.ApiResponse;
import uz.ilmnajot.organisation_task.payload.request.CalculateRequest;
import uz.ilmnajot.organisation_task.service.CalculationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/calculations")
public class CalculationController {


    private final CalculationService calculationService;

    @PostMapping("/create")
    private HttpEntity<ApiResponse> create(@RequestBody CalculateRequest request) {
        ApiResponse apiResponse = calculationService.create(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/update/{calculationId}")
    private HttpEntity<ApiResponse> update(@RequestBody CalculateRequest request,
                                           @PathVariable("calculationId") Long calculationId) {
        ApiResponse apiResponse = calculationService.update(request, calculationId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @GetMapping("/getCalculation/{calculationId}")
    private HttpEntity<ApiResponse> getById(@PathVariable("calculationId") Long id) {
        ApiResponse apiResponse = calculationService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getAll")
    private HttpEntity<ApiResponse> getAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        ApiResponse apiResponse = calculationService.getAll(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @GetMapping("/getAllDeletedCalculations")
    private HttpEntity<ApiResponse> getAllDeletedCalculations(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        ApiResponse apiResponse = calculationService.getAllDeletedCalculations(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @DeleteMapping("/delete/{calculationId}")
    private HttpEntity<ApiResponse> delete(@PathVariable("calculationId") Long id) {
        ApiResponse apiResponse = calculationService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @GetMapping("/get-info-with-salary")
    private HttpEntity<ApiResponse> getInformationWithSalary(@PathParam("month") String month) {
        ApiResponse apiResponse = calculationService.getInformationWithSalary(month);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/get-info-with-rate")
    private HttpEntity<ApiResponse> getInformationWithRate(@PathParam("rate") Double rate,
                                                           @PathParam("month") String month) {
        ApiResponse apiResponse = calculationService.getInformationWithRate(rate, month);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

}
