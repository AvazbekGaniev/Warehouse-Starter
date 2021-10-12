package warehouseapp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Warehouse;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.WarehouseRepository;
import warehouseapp.warehouse.service.WarehouseService;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    WarehouseService warehouseService;

    @PostMapping("/add")
    public ApiResponse save(@RequestBody Warehouse warehouse) {
        warehouseRepository.save(warehouse);
        return new ApiResponse("Saved!", true);
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok(new ApiResponse("List", true, warehouseRepository.findAll()));
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getOne(@PathVariable Integer id) {
        ApiResponse apiResponse = warehouseService.getOne(id);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        ApiResponse apiResponse = warehouseService.edit(id, warehouse);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }

    @GetMapping("/changeStatus/{id}")
    public HttpEntity<ApiResponse> changeStatus(@PathVariable Integer id) {
        ApiResponse apiResponse = warehouseService.changeStatus(id);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id) {
        if (!warehouseRepository.existsById(id)) return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        warehouseRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Delete!", true));
    }
}
