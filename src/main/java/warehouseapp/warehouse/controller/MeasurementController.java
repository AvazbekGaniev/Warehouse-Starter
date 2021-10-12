package warehouseapp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Measurement;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.MeasurementRepository;
import warehouseapp.warehouse.service.MeasurementService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    MeasurementService measurementService;

    @GetMapping("/list")
    public HttpEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(new ApiResponse("FOUND!", true, measurementRepository.findAll()));
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getOne(@PathVariable Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.map(measurement -> ResponseEntity.ok(new ApiResponse("FOUND!", true, measurement))).orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("NOT FOUND!", false)));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id) {
        if (!measurementRepository.existsById(id)) return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("NOT FOUND!", true));
            measurementRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("DELETED!", true));
    }

    @PostMapping("/add")
    public HttpEntity<ApiResponse> add(@Valid @RequestBody Measurement measurement) {
        measurementService.addMeasurement(measurement);
        return ResponseEntity.ok(new ApiResponse("Saved!", true));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id, @RequestBody Measurement measurement) {
        ApiResponse apiResponse = measurementService.edit(id, measurement);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));

    }
}
