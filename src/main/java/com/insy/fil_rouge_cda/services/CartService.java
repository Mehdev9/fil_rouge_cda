package com.insy.fil_rouge_cda.services;

import com.insy.fil_rouge_cda.models.Account;
import com.insy.fil_rouge_cda.models.CartEntity;
import com.insy.fil_rouge_cda.models.CartProductEntity;
import com.insy.fil_rouge_cda.models.ProductEntity;
import com.insy.fil_rouge_cda.repositories.CartProductRepository;
import com.insy.fil_rouge_cda.repositories.CartRepository;
import com.insy.fil_rouge_cda.repositories.IAccountRepository;
import com.insy.fil_rouge_cda.repositories.ProductRepository;
import com.insy.fil_rouge_cda.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartItemRepository;

    @Autowired
    private IAccountRepository accountRepository;

    public CartEntity addProductToCart(Long productId, int quantity, double price) {
        String userName = SecurityUtils.getCurrentUsername();
        CartEntity cart = cartRepository.findByUserUsername(userName);

        if (cart == null) {
            Account user = accountRepository.findByUsernameIgnoreCase(userName).orElseThrow();
            cart = new CartEntity();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        CartProductEntity existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {

            int updatedQuantity = existingItem.getQuantity() + quantity;
            existingItem.setQuantity(updatedQuantity);
            cartItemRepository.save(existingItem);
        } else {

            CartProductEntity newItem = new CartProductEntity();
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
            newItem.setPrice(price);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
            cartItemRepository.save(newItem);
        }

        return cartRepository.save(cart);
    }


    public CartEntity removeProductFromCart(Long productId) {
        String userName = SecurityUtils.getCurrentUsername();
        CartEntity cart = cartRepository.findByUserUsername(userName);
        if (cart != null) {
            cart.getItems().removeIf(item -> item.getProductId().equals(productId));
            cartRepository.save(cart);
        }
        return cart;
    }

    public CartEntity getCart() {
        String userName = SecurityUtils.getCurrentUsername();
        return cartRepository.findByUserUsername(userName);
    }

    public CartEntity updateProductQuantity(Long productId, int quantity) {
        String userName = SecurityUtils.getCurrentUsername();
        CartEntity cart = cartRepository.findByUserUsername(userName);

        if (cart == null) {
            return cart;
        }

        CartProductEntity existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            if (quantity <= 0) {
                cart.getItems().remove(existingItem);
            } else {
                existingItem.setQuantity(quantity);
                cartItemRepository.save(existingItem);
            }
        }

        return cartRepository.save(cart);
    }


}
