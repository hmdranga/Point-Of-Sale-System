/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dao.custom.impl.ItemdetailDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.pos.dao.custom.impl.QueryDAOImpl;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Itemdetail;

/**
 *
 * @author A C E R
 */
public class DAOFactory {

   private static DAOFactory dAOFactory;
    
    public DAOFactory() {
    }
    
    public static DAOFactory getInstance(){
        if(dAOFactory == null){
            dAOFactory = new DAOFactory();
        }
        return dAOFactory;
    }
    
    public enum DAOType{
        Customer,Item,Itemdetail,Order,Query
    }
    
    
    public SuperDAO getDAO(DAOType type ){
        switch(type){
            case Customer:
                return new CustomerDAOImpl();
            case Item:
                return new ItemDAOImpl();
            case Itemdetail:
                return new ItemdetailDAOImpl();
            case Order:
                return new OrdersDAOImpl();
            case Query:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
    
}
