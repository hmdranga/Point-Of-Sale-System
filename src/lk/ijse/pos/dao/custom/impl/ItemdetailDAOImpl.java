/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dao.custom.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.ItemdetailDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Itemdetail;
import lk.ijse.pos.entity.Itemdetail_PK;

/**
 *
 * @author Harsha madushan
 */
public class ItemdetailDAOImpl implements ItemdetailDAO{

    @Override
    public Boolean save(Itemdetail entity) throws Exception {
        return CrudUtil.executeUpdate("Insert into itemdetail values(?,?,?,?)", entity.getItemdetail_PK().getOrderId(),entity.getItemdetail_PK().getItemCode(),entity.getQty(),entity.getUnitPrice());
    }

    @Override
    public Boolean delete(Itemdetail_PK id) throws Exception {
        return CrudUtil.executeUpdate("Delete from itemdetail where orderId = ? and itemCode = ?", id.getOrderId(),id.getItemCode());
    }

    @Override
    public Boolean update(Itemdetail entity) throws Exception {
        return CrudUtil.executeUpdate("Update itemdetail set qty = ?, unitPrice = ? where orderId = ? and itemCode = ? ", entity.getQty(),entity.getUnitPrice(),entity.getItemdetail_PK().getOrderId(),entity.getItemdetail_PK().getItemCode());
    }

    @Override
    public ArrayList<Itemdetail> getAll() throws Exception {
        ArrayList<Itemdetail> itemdetails = new ArrayList<>();
        ResultSet rs = CrudUtil.executeQuery("Select * from itemdetail");
        while (rs.next()) {
            Itemdetail item = new Itemdetail(rs.getString(1), rs.getString(2), rs.getInt(3),BigDecimal.valueOf(rs.getDouble(4)));
            itemdetails.add(item);
        }
        return itemdetails;
    }

    @Override
    public Itemdetail findByID(Itemdetail_PK id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery("select * from itemdetail where orderId = ? and itemCode = ?", id.getOrderId(),id.getItemCode());
        Itemdetail itemdetail = new Itemdetail(rs.getString(1), rs.getString(2), rs.getInt(3),BigDecimal.valueOf(rs.getDouble(4)));
        return itemdetail;
    }
    
}
