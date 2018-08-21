/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import lk.ijse.pos.business.custom.OrdersBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrdersDAO;
import lk.ijse.pos.dto.OrdersDTO;
import lk.ijse.pos.entity.Orders;

/**
 *
 * @author A C E R
 */
public class OrdersBOImpl implements OrdersBO {
    
    private OrdersDAO ordersDAO;
    public OrdersBOImpl() {
        this.ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Order);
    }
    

    @Override
    public boolean saveOrders(OrdersDTO order) throws Exception {
        Orders Eorder = new Orders(order.getId(), order.getDate(), order.getCustomerId());
        return ordersDAO.save(Eorder);
    }

    @Override
    public boolean updateOrders(OrdersDTO order) throws Exception {
        Orders Eorder = new Orders(order.getId(), order.getDate(), order.getCustomerId());
        return ordersDAO.update(Eorder);
    }

    @Override
    public boolean deleteOrders(String id) throws Exception {
        return ordersDAO.delete(id);
    }

    @Override
    public OrdersDTO findByID(String id) throws Exception {
        Orders order = ordersDAO.findByID(id);
        OrdersDTO ordersDTO = new OrdersDTO(order.getId(), order.getDate(), order.getCustomerId());
        return ordersDTO;
    }

    @Override
    public ArrayList<OrdersDTO> getAllOrders() throws Exception {
        ArrayList<OrdersDTO> ordersDTOs = new ArrayList<>();
        ArrayList<Orders > orders = ordersDAO.getAll();
        for (Orders order : orders) {
            OrdersDTO ordersDTO = new OrdersDTO(order.getId(), order.getDate(), order.getCustomerId());
            ordersDTOs.add(ordersDTO);
        }
        return ordersDTOs;
    }



    
}
