package model;

public class Food {
	private String kode;
	private String name;
	private int price;
	private int quantity;
	private String detail;
	
	public Food() {
	    super();
	}
	
	public Food(String name, int price, int quantity,
			String detail) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.detail = detail;
	}
	
	public final String getKode() {
            return kode;
        }

        public final void setKode(String kode) {
            this.kode = kode;
        }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getDetail() {
		return this.detail;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
