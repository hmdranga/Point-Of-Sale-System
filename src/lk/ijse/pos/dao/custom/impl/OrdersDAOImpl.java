/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.OrdersDAO;
import lk.ijse.pos.entity.Orders;

/**
 *
 * @author A C E R
 */
public class OrdersDAOImpl implements OrdersDAO{

    @Override
    public Boolean save(Orders entity) throws Exception {
        return CrudUtil.executeUpdate("insert into orders values(?,?,?)", entity.getId(),entity.getDate(),entity.getCustomerId());
    }

    @Override
    public Boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("delete from orders where id = ?", id);
    }

    @Override
    public Boolean update(Orders entity) throws Exception {
        return CrudUtil.executeUpdate("Update orders set date = ?,customerId = ? where id = ?", entity.getDate(),entity.getId(),entity.getCustomerId());
    }

    @Override
    public ArrayList<Orders> getAll() throws Exception {
        ArrayList<Orders> orders = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("select * from orders");
        while(rs.next()){
            Orders order = new Orders(rs.getString(1), rs.getDate(2), rs.getString(3));
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Orders findByID(String id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from orders where id = ?", id);
        Orders order = new Orders(rs.getString(1), rs.getDate(2), rs.getString(3));
        return order;
    }
    
}
