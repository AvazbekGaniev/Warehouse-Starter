package warehouseapp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.ProductDTO;
import warehouseapp.warehouse.repository.CategoryRepository;
import warehouseapp.warehouse.repository.ProductRepository;
import warehouseapp.warehouse.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @PostMapping
    public HttpEntity<ApiResponse> save(@Valid @RequestBody ProductDTO productDTO) {
        ApiResponse apiResponse = productService.save(productDTO);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }

    @PutMapping
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody ProductDTO productDTO){
        ApiResponse apiResponse = productService.edit(id,productDTO);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }

    @GetMapping
    public HttpEntity<?> getList(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{catId}")
    public HttpEntity<ApiResponse> getByCatId( @PathVariable Integer catId) {
        ApiResponse apiResponse = productService.getByCatId(catId);
        if (!apiResponse.isSuccess()) return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Not Found!", false));
        return ResponseEntity.ok(new ApiResponse("Found!", true, apiResponse));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id) {
        if (!productRepository.existsById(id)) return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("NOT FOUND!", true));
        productRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("DELETED!", true));
    }
}
