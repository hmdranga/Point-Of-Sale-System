/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

/**
 *
 * @author A C E R
 */
public class ItemBOImpl implements ItemBO{
    
    private ItemDAO itemDAO;

    public ItemBOImpl() {
        this.itemDAO = (ItemDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Item);
    }
    
    @Override
    public boolean saveItem(ItemDTO item) throws Exception {
        Item saveitem = new Item( item.getCode() , item.getDescription(), item.getUnitPrice() , item.getQtyOnHand());
        return itemDAO.save(saveitem);
    }

    @Override
    public boolean updateItem(ItemDTO item) throws Exception {
       Item updateitem = new Item( item.getCode() , item.getDescription(), item.getUnitPrice() , item.getQtyOnHand());
       return itemDAO.update(updateitem);
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDAO.delete(id);
    }

    @Override
    public ItemDTO findByID(String id) throws Exception {
        Item getItem = itemDAO.findByID(id);
        ItemDTO sendItem = new ItemDTO(getItem.getCode(), getItem.getDescription(), getItem.getUnitPrice(),getItem.getQtyOnHand());
        return sendItem;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws Exception {
        ArrayList<Item> allItems = itemDAO.getAll();
        ArrayList<ItemDTO> sendItems = new ArrayList<>();
        for (Item item : allItems) {
            ItemDTO i = new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
            sendItems.add(i);
        }
        
        return sendItems;
    }

   
    
}
