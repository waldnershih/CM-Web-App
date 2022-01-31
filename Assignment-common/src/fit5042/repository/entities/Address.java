/**
 * 
 */
package fit5042.repository.entities;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Hsuan-Yu Shih
 *
 */
@Embeddable	
@Access(AccessType.PROPERTY)
public class Address implements Serializable{
	private String streetNumber;
    private String streetAddress;
    private String suburb;
    private String postcode;
    private String state;
    
    public Address() {
    }

    public Address(String streetNumber, String streetAddress, String suburb, String postcode, String state) {
        this.streetNumber = streetNumber;
        this.streetAddress = streetAddress;
        this.suburb = suburb;
        this.postcode = postcode;
        this.state = state;
    }
    
	@Column(name = "POSTCODE")
	public String getPostcode() {
		return postcode;
	}
	
	@Column(name = "STATE")
	public String getState() {
		return state;
	}
	
    @Column(name = "STREET_ADDRESS")
	public String getStreetAddress() {
		return streetAddress;
	}
    
    @Column(name = "STREET_NUMBER")
	public String getStreetNumber() {
		return streetNumber;
	}

    @Column(name = "SUBURB")
	public String getSuburb() {
		return suburb;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
    
    @Override
    public String toString() {
        return streetNumber + " " + streetAddress + ", " + suburb + ", " + state + " " + postcode;
    }    
}
