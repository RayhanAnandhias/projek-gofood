package model;

import java.util.List;

public class Restaurant {
	private String kode;
	private String name;
	private Location location;
	private String telp_no;
	private String detail;
	//private List<Picture> picture;
	private List<Food> foods;
	
	public Restaurant() {
	    super();
	}
	
	public Restaurant(String name, Location location,
			String telp_no, String detail, List<Food> foods) {
		super();
		this.name = name;
		this.location = location;
		this.telp_no = telp_no;
		this.detail = detail;
		this.foods = foods;
	}
	
	public final String getKode() {
            return kode;
        }

        public final void setKode(String kode) {
            this.kode = kode;
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

        public void setFoods(List<Food> foods) {
            this.foods = foods;
        }

        public List<Food> getFoods() {
            return foods;
        }
}
