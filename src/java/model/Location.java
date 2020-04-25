package model;

public class Location {
	private String id;
	private String street;
	private String city;
	
	public Location() {
		
	}
	
	public Location(String street, String city) {
		super();
		this.street = street;
		this.city = city;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
}
