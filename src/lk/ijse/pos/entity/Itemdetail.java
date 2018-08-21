/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.entity;

import java.math.BigDecimal;

/**
 *
 * @author A C E R
 */
public class Itemdetail {
    private Itemdetail_PK itemdetail_PK;
    private int qty;
    private BigDecimal unitPrice;

    public Itemdetail() {
    }

    public Itemdetail(Itemdetail_PK itemdetail_PK, int qty, BigDecimal unitPrice) {
        this.itemdetail_PK = itemdetail_PK;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
    
    public Itemdetail(String orderId,String itemCode, int qty, BigDecimal unitPrice) {
        this.itemdetail_PK =new Itemdetail_PK(orderId, itemCode);
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    /**
     * @return the itemdetail_PK
     */
    public Itemdetail_PK getItemdetail_PK() {
        return itemdetail_PK;
    }

    /**
     * @param itemdetail_PK the itemdetail_PK to set
     */
    public void setItemdetail_PK(Itemdetail_PK itemdetail_PK) {
        this.itemdetail_PK = itemdetail_PK;
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the unitPrice
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Itemdetail{" + "itemdetail_PK=" + itemdetail_PK + ", qty=" + qty + ", unitPrice=" + unitPrice + '}';
    }
    
}
