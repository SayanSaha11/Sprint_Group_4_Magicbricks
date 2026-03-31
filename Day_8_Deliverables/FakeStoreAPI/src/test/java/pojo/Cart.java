package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {

    private Integer        id;
    private Integer        userId;
    private String         date;
    private List<CartItem> products;

    public Cart() {}

    /** constructor for POST /carts payload */
    public Cart(Integer userId, List<CartItem> products) {
        this.userId   = userId;
        this.products = products;
    }

    public Integer        getId()               { return id; }
    public void           setId(Integer v)      { this.id = v; }

    public Integer        getUserId()           { return userId; }
    public void           setUserId(Integer v)  { this.userId = v; }

    public String         getDate()             { return date; }
    public void           setDate(String v)     { this.date = v; }

    public List<CartItem> getProducts()              { return products; }
    public void           setProducts(List<CartItem> v) { this.products = v; }

    // ── Inner: CartItem ───

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CartItem {
        private Integer productId;
        private Integer quantity;

        public CartItem() {}

        public CartItem(Integer productId, Integer quantity) {
            this.productId = productId;
            this.quantity  = quantity;
        }

        public Integer getProductId()          { 
        	return productId; 
        }
        
        public void setProductId(Integer v) { 
        	this.productId = v; 
        }

        public Integer getQuantity()           { 
        	return quantity; 
        }
        
        public void setQuantity(Integer v)  { 
        	this.quantity = v;
        }
    }
}
