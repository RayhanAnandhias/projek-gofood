package model;

public class Motor {
	private String id;
	private String no_plat;
	private String merk;
	
	public Motor() {
		
	}
	
	public Motor(String no_plat, String merk) {
		super();
		this.no_plat = no_plat;
		this.merk = merk;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNo_Plat() {
		return this.no_plat;
	}
	
	public void setNo_Plat(String no_plat) {
		this.no_plat = no_plat;
	}
	
	public String getMerk() {
		return this.merk;
	}
	
	public void setMerk(String merk) {
		this.merk = merk;
	}
}
