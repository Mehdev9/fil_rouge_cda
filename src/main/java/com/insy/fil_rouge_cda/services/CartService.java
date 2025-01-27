package com.insy.fil_rouge_cda.services;

import com.insy.fil_rouge_cda.models.Account;
import com.insy.fil_rouge_cda.models.CartEntity;
import com.insy.fil_rouge_cda.models.CartProductEntity;
import com.insy.fil_rouge_cda.repositories.CartProductRepository;
import com.insy.fil_rouge_cda.repositories.CartRepository;
import com.insy.fil_rouge_cda.repositories.IAccountRepository;
import com.insy.fil_rouge_cda.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

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

        CartProductEntity item = new CartProductEntity();
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setCart(cart);
        cart.getItems().add(item);

        cartItemRepository.save(item);
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

}
