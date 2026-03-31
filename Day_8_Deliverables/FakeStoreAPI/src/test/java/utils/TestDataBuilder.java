package utils;

import pojo.Cart;
import pojo.LoginRequest;
import pojo.Product;
import pojo.User;

import java.util.Collections;

/**
 * Factory class — builds all test request payloads.
 */

public final class TestDataBuilder {

    private TestDataBuilder() {}

    // ── Auth ──

    public static LoginRequest validLoginRequest(String username, String password) {
        return new LoginRequest(username, password);
    }

    // ── Product ──

    /** Payload for POST /products */
    public static Product newProduct() {
        return new Product(
                "Formal Black Suit",
                49.99,
                "Formal Black Suit made for Men for all events",
                "clothing",
                "https://fakestoreapi.com/img/placeholder.jpg"
        );
    }

    /** Payload for PUT /products/{id} */
    public static Product updatedProduct() {
        return new Product(
                "Updated Laptop Pro Max",
                1299.99,
                "Updated laptop description designed for Software Engineers",
                "electronics",
                "https://fakestoreapi.com/img/updated.jpg"
        );
    }

    // ── User ───

    /** Payload for POST /users */
    public static User newUser() {
        return new User(
                "1",
                "sayan_saha@testmail.com",
                "sayan@12345"
        );
    }

    /** Payload for PUT /users/{id} */
    public static User updatedUser() {
        return new User(
                "updated_sayan",
                "updated_sayan@testmail.com",
                "sayan@99"
        );
    }

    // ── Cart ──
    /** Payload for POST /carts and PUT /carts/{id} */
    public static Cart newCart(int userId, int productId, int quantity) {
        Cart.CartItem item = new Cart.CartItem(productId, quantity);
        return new Cart(userId, Collections.singletonList(item));
    }
}
