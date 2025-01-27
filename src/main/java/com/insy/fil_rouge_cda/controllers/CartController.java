package com.insy.fil_rouge_cda.controllers;

import com.insy.fil_rouge_cda.models.CartEntity;
import com.insy.fil_rouge_cda.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartEntity> addProductToCart(@RequestParam Long productId,
                                                       @RequestParam int quantity, @RequestParam double price) {
        CartEntity updatedCart = cartService.addProductToCart(productId, quantity, price);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartEntity> removeProductFromCart(@RequestParam Long productId) {
        System.out.println("delete" + productId);
        CartEntity updatedCart = cartService.removeProductFromCart(productId);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/user")
    public ResponseEntity<CartEntity> getCart() {
        CartEntity cart = cartService.getCart();
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }
}
