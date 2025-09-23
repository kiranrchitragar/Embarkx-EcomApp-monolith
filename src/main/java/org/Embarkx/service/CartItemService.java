package org.Embarkx.service;

import jakarta.transaction.Transactional;
import org.Embarkx.dto.CartItemRequest;
import org.Embarkx.entities.CartItem;
import org.Embarkx.entities.Product;
import org.Embarkx.entities.Users;
import org.Embarkx.repository.CartItemRepository;
import org.Embarkx.repository.ProductRepository;
import org.Embarkx.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemService {

    private final ProductRepository productRepository;
    private final UsersRepository usersRepository;
    private final CartItemRepository cartItemRepository;

    public CartItemService(ProductRepository productRepository, UsersRepository usersRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.usersRepository = usersRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public boolean addToCart(Long userId, CartItemRequest cartItemRequest) {
        // Look for product
        Optional<Product> productOptional = productRepository.findById(Long.valueOf(cartItemRequest.getProductId()));
        if(productOptional.isEmpty()){
            return false;
        }
        Product product = productOptional.get();
        if(product.getStockQuantity()<cartItemRequest.getQuantity()){
            return false;
        }

        Optional<Users> userDetailsOptional = usersRepository.findById(userId);
        if(userDetailsOptional.isEmpty()){
            return false;
        }
        Users users = userDetailsOptional.get();
        CartItem existingCartItem = cartItemRepository.findByUsersAndProduct(users,product);
        if(existingCartItem!=null){
            //update the quantity, product already exists in cart , so we update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity()+cartItemRequest.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        }else{
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUsers(users);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    public boolean deleteItemFromCart(Long userId, Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        Optional<Users> userDetailsOptional = usersRepository.findById(userId);
       if(productOptional.isPresent() && userDetailsOptional.isPresent()){
           cartItemRepository.deleteByUsersAndProduct(userDetailsOptional.get(),productOptional.get());
           return true;
       }
        return false;
    }

    public List<CartItem> getCartItemsForUser(Long userId) {
        return usersRepository.findById(userId)// first check if user exists if exists then go for next line
                .map(cartItemRepository::findByUsers)
                .orElseGet(List::of); // if no user, empty list is returned
    }

    public void clearCart(Long userId) {
        usersRepository.findById(userId).ifPresent(
                cartItemRepository::deleteByUsers);
    }
}
