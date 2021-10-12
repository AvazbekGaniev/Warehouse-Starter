package warehouseapp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Supplier;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.SupplierRepository;
import warehouseapp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    SupplierService supplierService;

    @PostMapping("/add")
    public HttpEntity<ApiResponse> add(@RequestBody Supplier supplier) {
        ApiResponse apiResponse = supplierService.save(supplier);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
       return ResponseEntity.ok( supplierRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id) {
        ApiResponse apiResponse = supplierService.getById(id);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id, @RequestBody Supplier supplier) {
        ApiResponse apiResponse = supplierService.update(id, supplier);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleted(@PathVariable Integer id) {
        if (!supplierRepository.existsById(id)) return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Xatolik!", false));
        supplierRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Found!", true));
    }
}
