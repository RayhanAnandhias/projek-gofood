package model;

public class User {
	private String id;
	private String full_name;
	private String email;
	private String telp_no;
	private Location location;
	private int saldo_gopay;

	public User() {
		
	}
	
	public User(String full_name, String email,
			String telp_no, Location location, int saldo_gopay) {
		super();
		this.full_name = full_name;
		this.email = email;
		this.telp_no = telp_no;
		this.location = location;
		this.saldo_gopay = saldo_gopay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelp_no() {
		return telp_no;
	}

	public void setTelp_no(String telp_no) {
		this.telp_no = telp_no;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getSaldo_gopay() {
		return saldo_gopay;
	}

	public void setSaldo_gopay(int saldo_gopay) {
		this.saldo_gopay = saldo_gopay;
	}
}
