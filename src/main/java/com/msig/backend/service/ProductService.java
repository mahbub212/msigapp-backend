package com.msig.backend.service;


import com.msig.backend.exception.ResourceNotFoundException;
import com.msig.backend.model.Product;
import com.msig.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Melihat semua produk
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long prodcutId) {
    Product product = productRepository.findById(prodcutId)
                .orElseThrow(()-> new ResourceNotFoundException("Product is not exists with given Id : " + prodcutId));
        return product;
    }

    // Mendapatkan daftar produk yang menunggu approval
    public List<Product> getPendingProducts() {
        return productRepository.findByStatus(Product.Status.PENDING);
    }

    // Menambahkan produk baru dengan status "Pending"
    public Product addProduct(Product product) {
        product.setStatus(Product.Status.PENDING);
        return productRepository.save(product);
    }

    // Mengupdate produk
    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setDescription(productDetails.getDescription());
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    // Menghapus produk
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Menyetujui produk
    public void approveProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStatus(Product.Status.APPROVED);
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    // Menolak produk
    public void rejectProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStatus(Product.Status.REJECTED);
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }
}
