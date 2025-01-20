package com.insy.fil_rouge_cda.controllers;

import com.insy.fil_rouge_cda.models.ProductEntity;
import com.insy.fil_rouge_cda.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductEntity> create(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("brand") String brand,
            @RequestParam("price") Double price,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setBrand(brand);
        product.setPrice(price);
        product.setQuantity(quantity);

        ProductEntity createdProduct = productService.createProduct(product, image);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> edit(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("brand") String brand,
            @RequestParam("price") Double price,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setBrand(brand);
        product.setPrice(price);
        product.setQuantity(quantity);

        ProductEntity updatedProduct = productService.editProduct(id, product, image);
        return updatedProduct != null ?
                new ResponseEntity<>(updatedProduct, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
