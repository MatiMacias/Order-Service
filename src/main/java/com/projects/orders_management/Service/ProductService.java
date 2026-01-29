package com.projects.orders_management.Service;

import com.projects.orders_management.Model.Product;
import com.projects.orders_management.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        if(productRepository.existsById(product.getId())){
            throw new IllegalArgumentException("This product already exists");
        }
        return productRepository.save(product);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product productById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id: "+id));

    }

    public Product uptadeProduct(Long id, Product product){
        Product newProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: "+id));

        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());

        return productRepository.save(newProduct);
    }

    public void deleteProduct(Long id){
        Product product = productById(id);
        productRepository.deleteById(id);
    }

}
