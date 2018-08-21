/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom.impl;

import java.sql.Connection;
import java.util.ArrayList;
import lk.ijse.pos.business.custom.PlaceOrderBO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.ItemdetailDTO;
import lk.ijse.pos.dto.OrdersDTO;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    private ItemdetailBOImpl itemdetailBOImpl;
    private OrdersBOImpl ordersBOImpl;

    public PlaceOrderBOImpl() {
        this.itemdetailBOImpl = new ItemdetailBOImpl();
        this.ordersBOImpl = new OrdersBOImpl();
    }
    
    

    @Override
    public boolean placeOrder(OrdersDTO ordersDTO, ArrayList<ItemdetailDTO> item) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnection.getInstance().getConnection();
            conn.setAutoCommit(false);

            boolean result1 = ordersBOImpl.saveOrders(new OrdersDTO(ordersDTO.getId(),
                    ordersDTO.getDate(), ordersDTO.getCustomerId()));
            if (result1 == true) {

                for (ItemdetailDTO itemdetailDTO : item) {
                    ItemdetailDTO itemdetailDTO1 = new ItemdetailDTO(itemdetailDTO.getOrderId(), itemdetailDTO.getItemCode(),itemdetailDTO.getQty(), itemdetailDTO.getUnitPrice());
                    boolean result2 = itemdetailBOImpl.saveItemdetail(itemdetailDTO1);
                    
                    if (!result2) {
                        conn.rollback();
                        return false;
                    }
                }
            } else {
                conn.rollback();
                return false;

            }

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
           
            conn.setAutoCommit(true);

        }
        return true;

    }

}
