package com.projects.orders_management.Controller;

import com.projects.orders_management.DTO.product.ProductCreateRequest;
import com.projects.orders_management.DTO.product.ProductResponse;
import com.projects.orders_management.DTO.product.ProductUpdateRequest;
import com.projects.orders_management.Model.Product;
import com.projects.orders_management.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductCreateRequest request){
        return productService.createProduct(request);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse productById(@PathVariable Long id){
        return productService.productById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id,
                                 @Valid @RequestBody ProductUpdateRequest request){
        return productService.uptadeProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
