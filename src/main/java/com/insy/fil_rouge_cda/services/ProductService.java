package com.insy.fil_rouge_cda.services;

import com.insy.fil_rouge_cda.models.ProductEntity;
import com.insy.fil_rouge_cda.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private static final String UPLOAD_DIR = "/path/to/upload/directory/";

    public ProductEntity createProduct(ProductEntity product, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            try {
                String filePath = saveFile(image);
                product.setImageUrl(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return productRepository.save(product);
    }

    public ProductEntity editProduct(Long id, ProductEntity product, MultipartFile image) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            ProductEntity updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setCategory(product.getCategory());
            updatedProduct.setBrand(product.getBrand());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setQuantity(product.getQuantity());

            if (image != null && !image.isEmpty()) {
                try {
                    String filePath = saveFile(image);
                    updatedProduct.setImageUrl(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return productRepository.save(updatedProduct);
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private String saveFile(MultipartFile file) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.copy(file.getInputStream(), path);
        return path.toString();
    }
}
