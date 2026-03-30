package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Integer id;
    private String  email;
    private String  username;
    private String  password;
    private String  phone;
    private Name    name;
    private Address address;

    public User() {}

    /** Convenience constructor for POST /users payload */
    public User(String username, String email, String password) {
        this.username = username;
        this.email    = email;
        this.password = password;
    }

    public Integer getId()               { return id; }
    public void    setId(Integer v)      { this.id = v; }

    public String  getEmail()            { return email; }
    public void    setEmail(String v)    { this.email = v; }

    public String  getUsername()         { return username; }
    public void    setUsername(String v) { this.username = v; }

    public String  getPassword()         { return password; }
    public void    setPassword(String v) { this.password = v; }

    public String  getPhone()            { return phone; }
    public void    setPhone(String v)    { this.phone = v; }

    public Name    getName()             { return name; }
    public void    setName(Name v)       { this.name = v; }

    public Address getAddress()          { return address; }
    public void    setAddress(Address v) { this.address = v; }

    // ── Inner: Name ───────────────────────────────────────────────────────────

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Name {
        private String firstname;
        private String lastname;

        public Name() {}

        public String getFirstname()           { return firstname; }
        public void   setFirstname(String v)   { this.firstname = v; }

        public String getLastname()            { return lastname; }
        public void   setLastname(String v)    { this.lastname = v; }
    }

    // ── Inner: Address ────────────────────────────────────────────────────────

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
        private String      city;
        private String      street;
        private Integer     number;
        private String      zipcode;
        private Geolocation geolocation;

        public Address() {}

        public String      getCity()                  { return city; }
        public void        setCity(String v)           { this.city = v; }

        public String      getStreet()                 { return street; }
        public void        setStreet(String v)         { this.street = v; }

        public Integer     getNumber()                 { return number; }
        public void        setNumber(Integer v)        { this.number = v; }

        public String      getZipcode()                { return zipcode; }
        public void        setZipcode(String v)        { this.zipcode = v; }

        public Geolocation getGeolocation()            { return geolocation; }
        public void        setGeolocation(Geolocation v){ this.geolocation = v; }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Geolocation {
            private String lat;
            private String lon;

            public Geolocation() {}

            public String getLat()         { return lat; }
            public void   setLat(String v) { this.lat = v; }

            public String getLon()         { return lon; }
            public void   setLon(String v) { this.lon = v; }
        }
    }
}
