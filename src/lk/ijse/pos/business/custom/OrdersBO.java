/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom;

import java.util.ArrayList;
import lk.ijse.pos.business.SuperBO;
import lk.ijse.pos.dto.OrdersDTO;

/**
 *
 * @author A C E R
 */
public interface OrdersBO extends SuperBO {
    
    public boolean saveOrders(OrdersDTO order)throws Exception;
    
    public boolean updateOrders(OrdersDTO order)throws Exception;
    
    public boolean  deleteOrders(String id)throws Exception;
    
    public OrdersDTO findByID(String id)throws Exception;
    
    public ArrayList<OrdersDTO> getAllOrders()throws Exception;
    
}
