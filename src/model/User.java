package model;


public class User {
    private String kode;
    private String full_name;
    private String email;
    private String password;
    private String telp_no;
    private Location location;
    private int saldo_gopay;

    public User() {
        super();
    }

    public User(String full_name, String email, String password,
                    String telp_no, Location location, int saldo_gopay) {
        super();
        this.full_name = full_name;
        this.email = email;
        this.telp_no = telp_no;
        this.location = location;
        this.saldo_gopay = saldo_gopay;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
