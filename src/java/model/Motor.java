package model;

public class Motor {
	private String kode;
	private String no_plat;
	private String merk;
	
	public Motor() {
            super();
	}
	
	public Motor(String no_plat, String merk) {
		super();
		this.no_plat = no_plat;
		this.merk = merk;
	}
	
	public final String getKode() {
            return kode;
        }

        public final void setKode(String kode) {
            this.kode = kode;
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
