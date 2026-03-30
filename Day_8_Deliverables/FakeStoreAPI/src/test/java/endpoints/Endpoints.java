package endpoints;

public final class Endpoints {

    private Endpoints() {}

    // Auth
    public static final String LOGIN         = "/auth/login";

    // Products
    public static final String PRODUCTS      = "/products";
    public static final String PRODUCT_BY_ID = "/products/{id}";

    // Users
    public static final String USERS         = "/users";
    public static final String USER_BY_ID    = "/users/{id}";

    // Carts
    public static final String CARTS         = "/carts";
    public static final String CART_BY_ID    = "/carts/{id}";
}
