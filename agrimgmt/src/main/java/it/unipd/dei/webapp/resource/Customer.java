package it.unipd.dei.webapp.resource;

import java.util.UUID;

/**
 * Represent the entity Customer in the database.
 */
public class Customer {

    private final UUID customerId;

    private final String name;

    private final String country;

    private final String city;

    private final String street;

    /**
     * Create a new object representing a Customer
     *
     * @param customerId UUID of the customer
     * @param name name of the customer
     * @param country country of the customer
     * @param city city of the customer
     * @param street street of the customer
     */
    public Customer(final UUID customerId, final String name, final String country, final String city, final String street){
        this.customerId = customerId;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    /**
     * @return the customer UUID
     */
    public final UUID getCustomerId(){
        return customerId;
    }

    /**
     * @return the customer name
     */
    public final String getName(){
        return name;
    }

    /**
     * @return the customer country
     */
    public final String getCountry(){
        return country;
    }

    /**
     * @return the customer city
     */
    public final String getCity(){
        return city;
    }

    /**
     * @return the customer street
     */
    public final String getStreet(){
        return street;
    }

}
