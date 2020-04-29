package model;

public class Driver {
	private String kode;
	private String full_name;
	private String email;
	private String telp_no;
	private Motor motor;
	private Location location;
        private Double jarakToResto;
	
	public Driver() {
	    super();
	}
	
	public Driver(String full_name, String email,
			String telp_no, Motor motor, 
			Location location) {
		super();
		this.full_name = full_name;
		this.email = email;
		this.telp_no = telp_no;
		this.motor = motor;
		this.location = location;
	}
	
	public final String getKode() {
            return kode;
        }

        public final void setKode(String kode) {
            this.kode = kode;
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
	
	public Motor getMotor() {
		return this.motor;
	}
	
	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

        public void setJarakToResto(Double jarakToResto) {
            this.jarakToResto = jarakToResto;
        }

        public Double getJarakToResto() {
            return jarakToResto;
        }
        
        
}
