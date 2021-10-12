package warehouseapp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.entity.Currency;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.repository.CurrencyRepository;
import warehouseapp.warehouse.service.CurrencyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    CurrencyService currencyService;

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody Currency currency) {
        currencyService.addCurrency(currency);
        return ResponseEntity.ok( new ApiResponse(" Saved!", true));
    }

    @GetMapping("/list")
    public HttpEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(new ApiResponse("Succeed!",true,currencyRepository.findAll()));
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getById(@PathVariable Integer id) {
        Optional byId = currencyRepository.findById(id);
        if (!byId.isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, byId.get()));
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id, Currency currency) {
        ApiResponse apiResponse = currencyService.edit(id, currency);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleted(@PathVariable Integer id) {
        if (!currencyRepository.existsById(id)) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error!", false));
        currencyRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Deleted !", true));
    }

    @GetMapping("/changeStatus/{id}")
    public HttpEntity<ApiResponse> change(@PathVariable Integer id) {
        ApiResponse apiResponse = currencyService.changeStatus(id);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }
}
