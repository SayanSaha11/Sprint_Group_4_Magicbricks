package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Integer id;
    private String  title;
    private Double  price;
    private String  description;
    private String  category;
    private String  image;
    private Rating  rating;

    public Product() {}

    public Product(String title, Double price, String description,
                   String category, String image) {
        this.title       = title;
        this.price       = price;
        this.description = description;
        this.category    = category;
        this.image       = image;
    }

    public Integer getId()               { return id; }
    public void    setId(Integer v)      { this.id = v; }

    public String  getTitle()            { return title; }
    public void    setTitle(String v)    { this.title = v; }

    public Double  getPrice()            { return price; }
    public void    setPrice(Double v)    { this.price = v; }

    public String  getDescription()           { return description; }
    public void    setDescription(String v)   { this.description = v; }

    public String  getCategory()         { return category; }
    public void    setCategory(String v) { this.category = v; }

    public String  getImage()            { return image; }
    public void    setImage(String v)    { this.image = v; }

    public Rating  getRating()           { return rating; }
    public void    setRating(Rating v)   { this.rating = v; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Rating {

        private Double  rate;
        private Integer count;

        public Rating() {}

        public Double  getRate()           { return rate; }
        public void    setRate(Double v)   { this.rate = v; }

        public Integer getCount()          { return count; }
        public void    setCount(Integer v) { this.count = v; }
    }
}
