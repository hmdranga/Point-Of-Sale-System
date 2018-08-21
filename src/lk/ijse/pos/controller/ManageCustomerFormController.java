/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pos.business.BOFactory;
import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.main.StartUp;
import lk.ijse.pos.view.util.tblmodel.CustomerTM;

/**
 * FXML Controller class
 *
 * @author A C E R
 */
public class ManageCustomerFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtCustomerId;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtCustomerAddress;
    @FXML
    private TableView<CustomerTM> tblCustomers;
    
    private boolean decide = false;

    /**
     * Initializes the controller class.
     */
    
    private CustomerBO customerBO; 

    public ManageCustomerFormController() {
        this.customerBO= (CustomerBO)BOFactory.getInstance().getBO(BOFactory.BOType.CustomerBO);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        LoadAllCustomers();
    }    

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
            StartUp.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }

    @FXML
    private void btnAddNewCustomer_OnAction(ActionEvent event) {
           decide = true;
           txtCustomerId.setText("");
           txtCustomerName.setText("");
           txtCustomerAddress.setText("");
           tblCustomers.getSelectionModel().clearSelection();
        
    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {
        
        if(decide){
            saveCustomer();
            LoadAllCustomers();
          
        }else if(tblCustomers.getSelectionModel().getSelectedIndex()>=0 && decide ==false){
            updateCustomer();
            //new Alert(Alert.AlertType.CONFIRMATION, "Customer Update successful..", ButtonType.OK).show();
            LoadAllCustomers();
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Please press the Add new Button to add Customer..", ButtonType.OK).show();
        }
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        
       
        
        if(tblCustomers.getSelectionModel().getSelectedIndex()>=0){
            deleteCustomer();
            new Alert(Alert.AlertType.CONFIRMATION, "Customer delete successful..", ButtonType.OK).show();
            LoadAllCustomers();
        }else{
            new Alert(Alert.AlertType.ERROR, "Please select a customer to delete..", ButtonType.OK).show();
        }
        
    }
    
    
    private void saveCustomer(){
        String cid = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        CustomerDTO customer = new CustomerDTO(cid, name, address);
        
        try {
            Boolean result = customerBO.saveCustomer(customer);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Customer has been saved successfully", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Error on saving customer", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadAllCustomers(){
        try {
            ArrayList<CustomerDTO> AllCustomers = customerBO.getAllCustomer();
            ArrayList<CustomerTM> addCustomers = new ArrayList<>();
            for (CustomerDTO AllCustomer : AllCustomers) {
                CustomerTM customer = new CustomerTM(AllCustomer.getId(), AllCustomer.getName(), AllCustomer.getAddress());
                addCustomers.add(customer);
            }
            tblCustomers.setItems(FXCollections.observableArrayList(addCustomers));
            
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   private void updateCustomer(){
        CustomerTM customertbl =  tblCustomers.getSelectionModel().getSelectedItem();
        String id = customertbl.getId();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        CustomerDTO customer = new CustomerDTO(id, name, address);
        
        try {
            Boolean result = customerBO.updateCustomer(customer);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Customer has been Updated successfully..", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Error on update customer..", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
   
   private void deleteCustomer(){
       CustomerTM customer = tblCustomers.getSelectionModel().getSelectedItem();
       String id = customer.getId();
        try {
            boolean result = customerBO.deleteCustomer(id);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Customer has been deleted successfully..", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Error when deleting customer..", ButtonType.OK).show();
            }            
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }    
}
