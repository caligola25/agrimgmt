package it.unipd.dei.webapp.resource;

/**
 * Represents the entity Supplier of the database.
 */
public class Supplier {

    private final String name;
    private final String country;

/**
 * Creates an object representing a new Supplier
 *
 * @param name the name of the supplier
 * @param country the country of the supplier
 * */
    public Supplier(final String name, final String country){
        this.name=name;
        this.country=country;
    }

    /**
     * @return the name of the supplier
     */
    public String getName() { return name; }

    /**
     * @return the country of the supplier
     */
    public String getCountry() { return country; }

}
