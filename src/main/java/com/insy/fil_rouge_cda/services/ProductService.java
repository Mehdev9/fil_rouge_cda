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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/img/";

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> searchByName(List<ProductEntity> products, String query) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<ProductEntity> filterByPrice(List<ProductEntity> products, String price) {
        if (price.equals("low-high")) {
            return products.stream()
                    .sorted(Comparator.comparing(ProductEntity::getPrice))
                    .collect(Collectors.toList());
        } else if (price.equals("high-low")) {
            return products.stream()
                    .sorted(Comparator.comparing(ProductEntity::getPrice).reversed())
                    .collect(Collectors.toList());
        }
        return products;
    }

    public List<ProductEntity> filterByCategory(List<ProductEntity> products, String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<ProductEntity> filterByBrand(List<ProductEntity> products, String brand) {
        return products.stream()
                .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

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
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(file.getInputStream(), path);
        return "img/" + fileName;
    }

    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

}

