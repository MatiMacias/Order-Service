package com.projects.orders_management.Service;

import com.projects.orders_management.DTO.product.ProductCreateRequest;
import com.projects.orders_management.DTO.product.ProductResponse;
import com.projects.orders_management.DTO.product.ProductUpdateRequest;
import com.projects.orders_management.Model.Product;
import com.projects.orders_management.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductCreateRequest request){
        if(productRepository.existsByName(request.getName())){
            throw new IllegalArgumentException("This product already exists");
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        Product saved = productRepository.save(product);

        return mapToResponse(saved);
    }

    public List<ProductResponse> findAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse productById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id: "+id));

        return mapToResponse(product);
    }

    public ProductResponse uptadeProduct(Long id, ProductUpdateRequest request){
        Product newProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: "+id));

        newProduct.setName(request.getName());
        newProduct.setPrice(request.getPrice());

        Product update = productRepository.save(newProduct);

        return mapToResponse(update);
    }

    public void deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public Product getProductEntityById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Product not found"
                ));
    }

    private ProductResponse mapToResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        return response;
    }

}
