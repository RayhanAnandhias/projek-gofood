/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class ItemPesanan {
    private String idFood;
    private int quantity;

    public ItemPesanan() {
        super();
    }

    public ItemPesanan(String idFood, int quantity) {
        super();
        this.idFood = idFood;
        this.quantity = quantity;
    }

    public String getIdFood() {
        return idFood;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
