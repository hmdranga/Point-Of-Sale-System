/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import lk.ijse.pos.business.custom.ItemdetailBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemdetailDAO;
import lk.ijse.pos.dto.ItemdetailDTO;
import lk.ijse.pos.entity.Itemdetail;
import lk.ijse.pos.entity.Itemdetail_PK;

/**
 *
 * @author A C E R
 */
public class ItemdetailBOImpl implements ItemdetailBO{

    private ItemdetailDAO itemdetailDAO;

    public ItemdetailBOImpl() {
        this.itemdetailDAO = (ItemdetailDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Itemdetail);
    }
    
    
    @Override
    public boolean saveItemdetail(ItemdetailDTO itemdetail) throws Exception {
        Itemdetail Eitemdetail = new Itemdetail(itemdetail.getOrderId(), itemdetail.getItemCode(), itemdetail.getQty(), itemdetail.getUnitPrice());
        return itemdetailDAO.save(Eitemdetail);
    }

    @Override
    public boolean updateItemdetail(ItemdetailDTO itemdetail) throws Exception {
        Itemdetail Eitemdetail = new Itemdetail(itemdetail.getOrderId(), itemdetail.getItemCode(), itemdetail.getQty(), itemdetail.getUnitPrice());
        return itemdetailDAO.update(Eitemdetail);
    }

    @Override
    public boolean deleteItemdetail(String OrderID, String ItemCode) throws Exception {
        Itemdetail_PK itemdetail_PK = new Itemdetail_PK(OrderID, ItemCode);
        return itemdetailDAO.delete(itemdetail_PK);
    }

    @Override
    public ItemdetailDTO findByID(String OrderID, String ItemCode) throws Exception {
        Itemdetail_PK itemdetail_PK = new Itemdetail_PK(OrderID, ItemCode);
        Itemdetail itemdetail = itemdetailDAO.findByID(itemdetail_PK);
        ItemdetailDTO itemdetaiDTO = new ItemdetailDTO(itemdetail.getItemdetail_PK().getOrderId(), itemdetail.getItemdetail_PK().getItemCode(), itemdetail.getQty(), itemdetail.getUnitPrice());
        return itemdetaiDTO;
    }

    @Override
    public ArrayList<ItemdetailDTO> getAllItemdetail() throws Exception {
        ArrayList<ItemdetailDTO> itemdetailDTOs = new ArrayList<>();
        ArrayList<Itemdetail> itemdetails = itemdetailDAO.getAll();
        for (Itemdetail itemdetail : itemdetails) {
            ItemdetailDTO itemdetailDTO = new ItemdetailDTO(itemdetail.getItemdetail_PK().getOrderId(), itemdetail.getItemdetail_PK().getItemCode(), itemdetail.getQty(), itemdetail.getUnitPrice());
            itemdetailDTOs.add(itemdetailDTO);
        }
        return itemdetailDTOs;
    }

  
  
}
