/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;

/**
 *
 * @author A C E R
 */
public class ItemDAOImpl implements ItemDAO {

    @Override
    public Boolean save(Item entity) throws Exception {
        return CrudUtil.executeUpdate("Insert into item values(?,?,?,?)", entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());
    }

    @Override
    public Boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("Delete from item where code = ?", id);
    }

    @Override
    public Boolean update(Item entity) throws Exception {
        return CrudUtil.executeUpdate("Update item set description = ?, unitPrice = ? ,qtyOnHand = ? where code = ?", entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        ArrayList<Item> Items = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("Select * from item");
        while (rs.next()) {
            Item item = new Item(rs.getString(1), rs.getString(2),rs.getBigDecimal(3),rs.getInt(4));
            Items.add(item);
        }
        return Items;
    }

    @Override
    public Item findByID(String id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from item where code= ?", id);
        rs.next();
        Item item = new Item(rs.getString(1), rs.getString(2), rs.getBigDecimal(3),rs.getInt(4));
        return item;
    }
    
}
