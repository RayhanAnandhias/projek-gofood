package model;

import java.util.List;

public class Restaurant {
	private String id;
	private String name;
	private Location location;
	private String telp_no;
	private String detail;
	//private List<Picture> picture;
	private List<Food> menu;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String name, Location location,
			String telp_no, String detail, List<Food> menu) {
		super();
		this.name = name;
		this.location = location;
		this.telp_no = telp_no;
		this.detail = detail;
		this.menu = menu;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getTelp_no() {
		return this.telp_no;
	}

	public void setTelp_no(String telp_no) {
		this.telp_no = telp_no;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
