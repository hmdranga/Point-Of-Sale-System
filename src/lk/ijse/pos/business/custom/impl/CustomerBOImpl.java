/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

/**
 *
 * @author A C E R
 */
public class CustomerBOImpl implements CustomerBO{
    
    private CustomerDAO customerDAO;

    public CustomerBOImpl() {
        this.customerDAO =(CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Customer);
    }
    
    @Override
    public boolean saveCustomer(CustomerDTO customer) throws Exception {
        Customer customerE = new Customer(customer.getId(), customer.getName(), customer.getAddress());
        return customerDAO.save(customerE);
        
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        Customer custE = new Customer(customer.getId(), customer.getName(), customer.getAddress());
        return customerDAO.update(custE);
        
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }


    @Override
    public CustomerDTO findByID(String id) throws Exception {
        Customer customer = customerDAO.findByID(id);
        CustomerDTO customerDTO = new CustomerDTO(customer.getcID(), customer.getName(), customer.getAddress());
        
        return customerDTO;
        
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
       ArrayList<CustomerDTO> array = new ArrayList<>();
       ArrayList<Customer> ecustomers = customerDAO.getAll();
        for (Customer customer : ecustomers) {
            CustomerDTO cust = new CustomerDTO(customer.getcID(), customer.getName(), customer.getAddress());
            array.add(cust);
        }
       return array;
    }
    
}
