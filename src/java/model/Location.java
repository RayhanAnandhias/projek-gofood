package model;

public class Location {
	private String kode;
	private String street;
	private String city;
	
	public Location() {
	    super();
	}
	
	public Location(String street, String city) {
		super();
		this.street = street;
		this.city = city;
	}
	
	public final String getKode() {
        return kode;
    }

    public final void setKode(String kode) {
        this.kode = kode;
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
