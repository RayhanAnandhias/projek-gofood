package model;

import java.util.Date;
import java.util.List;

public class Pesanan {
	private String kode;
	private String user_id;
	private String restaurant_id;
	private String driver_id;
	private List<ItemPesanan> pesanan;
	private boolean payment_method;
	private int ongkir;
	private int total_cost;
	private Date date;
	
	public Pesanan() {
            super();
	}
	
	public Pesanan(String user_id, String restaurant_id,
			String driver_id, List<ItemPesanan> pesanan,
			boolean payment_method, int ongkir, int total_cost) {

		super();
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
		this.driver_id = driver_id;
		this.pesanan = pesanan;
		this.payment_method = payment_method;
		this.ongkir = ongkir;
		this.total_cost = total_cost;
		this.date = new Date();
	}
	
	public final String getKode() {
            return kode;
        }

	public final void setKode(String kode) {
            this.kode = kode;
        }
	
	public String getUserId() {
		return this.user_id;
	}
	
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}
	
	public String getRestaurantId() {
		return this.restaurant_id;
	}
	
	public void setRestaurantId(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	
	public String getDriverId() {
		return this.driver_id;
	}
	
	public void setDriverId(String driver_id) {
		this.driver_id = driver_id;
	}
	
	public List<ItemPesanan> getPesanan(){
		return this.pesanan;
	}
	
	public void setPesanan(List<ItemPesanan> pesanan) {
		this.pesanan = pesanan;
	}
	
	public boolean getPaymentMethod() {
		return this.payment_method;
	}
	
	public void setPaymentMethod(boolean payment_method) {
		this.payment_method = payment_method;
	}
	
	public int getOngkir() {
		return this.ongkir;
	}
	
	public void setOngkir(int ongkir) {
		this.ongkir = ongkir;
	}
	
	public int getTotalCost() {
		return this.total_cost;
	}
	
	public void setTotalCost(int total_cost) {
		this.total_cost = total_cost;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
