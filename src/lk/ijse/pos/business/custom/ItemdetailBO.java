/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom;

import java.util.ArrayList;
import lk.ijse.pos.business.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemdetailDTO;

/**
 *
 * @author A C E R
 */
public interface ItemdetailBO extends SuperBO{
    
    public boolean saveItemdetail(ItemdetailDTO itemdetail)throws Exception;
    
    public boolean updateItemdetail(ItemdetailDTO itemdetail)throws Exception;
    
    public boolean  deleteItemdetail(String OrderID,String ItemCode)throws Exception;
    
    public ItemdetailDTO findByID(String OrderID,String ItemCode)throws Exception;
    
    public ArrayList<ItemdetailDTO> getAllItemdetail()throws Exception;
    
}
