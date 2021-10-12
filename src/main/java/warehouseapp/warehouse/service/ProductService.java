package warehouseapp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouseapp.warehouse.entity.Category;
import warehouseapp.warehouse.entity.Measurement;
import warehouseapp.warehouse.entity.Product;
import warehouseapp.warehouse.payload.ApiResponse;
import warehouseapp.warehouse.payload.ProductDTO;
import warehouseapp.warehouse.repository.*;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public ApiResponse save(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCode(UUID.randomUUID().toString());

        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
        product.setCategory(categoryOptional.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        product.setMeasurement(optionalMeasurement.get());

        productRepository.save(product);
        return new ApiResponse("Save", true);
    }

    public ApiResponse getByCatId(Integer catId) {
        return new ApiResponse("Mana", true, productRepository.findAllByCategoryId(catId));
    }

    public ApiResponse edit(Integer id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) return new ApiResponse("Not Found",false);
        Product product = optionalProduct.get();
        product.setName(productDTO.getName());
        product.setCode(UUID.randomUUID().toString());

        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
        product.setCategory(categoryOptional.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        product.setMeasurement(optionalMeasurement.get());

        productRepository.save(product);
        return new ApiResponse("Save", true);
    }
}
