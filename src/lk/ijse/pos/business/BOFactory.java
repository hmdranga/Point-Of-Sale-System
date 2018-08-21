/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business;

import lk.ijse.pos.business.custom.impl.CustomerBOImpl;
import lk.ijse.pos.business.custom.impl.ItemBOImpl;
import lk.ijse.pos.business.custom.impl.ItemdetailBOImpl;
import lk.ijse.pos.business.custom.impl.OrdersBOImpl;

/**
 *
 * @author Harsha madushan
 */
public class BOFactory {
    
    private static BOFactory bOFactory;
    
    private BOFactory(){
    
    }
    
    public static BOFactory getInstance(){
        if(bOFactory == null){
            bOFactory = new BOFactory();
        }
        
        return bOFactory;
    }
    
    public enum BOType{
        CustomerBO,ItemBO,ItemdetailBO,OrdersBO
    }
    
    public SuperBO getBO(BOType boType){
        switch(boType){
            case CustomerBO :
                return new CustomerBOImpl();
            case ItemBO:
                return new ItemBOImpl();
            case ItemdetailBO:
                return new ItemdetailBOImpl();
            case OrdersBO:
                return new OrdersBOImpl();
            default:
                return null;
                
        }
        
    }
        

}
