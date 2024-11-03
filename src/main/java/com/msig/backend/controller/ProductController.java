package com.msig.backend.controller;

import com.msig.backend.model.Product;
import com.msig.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //Melihat daftar semua product
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    //Melihat product by id
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductId(@PathVariable("id") Long Id) {
        Product product = productService.getProductById(Id);
        return ResponseEntity.ok(product);
    }

    //Menambah product baru
    @PostMapping
    public ResponseEntity<Product> addProduct(@Validated @RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    //Mengedit product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Validated @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    //Menghapus product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    //Product pending
    @GetMapping("/pending")
    public List<Product> getPendingProducts() {
        return productService.getPendingProducts();
    }

    //Product approve
    @PutMapping("/approve/{id}")
    public ResponseEntity<Void> approveProduct(@PathVariable Long id) {
        productService.approveProduct(id);
        return ResponseEntity.noContent().build();
    }

    //Product reject
    @PutMapping("/reject/{id}")
    public ResponseEntity<Void> rejectProduct(@PathVariable Long id) {
        productService.rejectProduct(id);
        return ResponseEntity.noContent().build();
    }
}
