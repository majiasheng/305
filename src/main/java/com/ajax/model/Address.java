package com.ajax.model;

import javax.validation.constraints.Pattern;


/**
 *
 * @author majiasheng
 */
public class Address {

    
    private String street;
    private String city;
    private State state;
    @Pattern(regexp = "^\\d{6}", message = "Invalid zip code, it must be a 6-digit zip code")
    private int zipCode;

    public Address(){
        super();
    }
    public Address(String street, String city, String state, int zipCode) {
	    this.street = street;
	    this.city = city;
	    this.state = State.valueOf(state);
	    this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(String state) {
        this.state = State.valueOf(state);
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return street + " "
                + city + " "
                + state.name() + " "
                + zipCode;
    }

}
