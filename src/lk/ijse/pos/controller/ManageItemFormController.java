/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.layout.region.Margins;
import java.io.IOException;
import java.math.BigDecimal;
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
import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.main.StartUp;
import lk.ijse.pos.view.util.tblmodel.ItemTM;

/**
 * FXML Controller class
 *
 * @author A C E R
 */
public class ManageItemFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtItemCode;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private TableView<ItemTM> tblItems;
    @FXML
    private JFXTextField txtQty;
    
    private boolean decide = false;
    
    
    private ItemBO itemBO;

    public ManageItemFormController() {
        this.itemBO = (ItemBO)BOFactory.getInstance().getBO(BOFactory.BOType.ItemBO);
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        LoadAllItems();
    }    

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
           StartUp.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }

    @FXML
    private void btnAddNewItem_OnAction(ActionEvent event) {
        decide = true;
           txtItemCode.setText("");
           txtDescription.setText("");
           txtUnitPrice.setText("");
           txtQty.setText("");
           tblItems.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {
        if(decide == true && !(tblItems.getSelectionModel().getSelectedIndex()>=0) ){
            saveItem();
            LoadAllItems();
        }else if(tblItems.getSelectionModel().getSelectedIndex()>=0 && decide ==false){
            updateItem();
            LoadAllItems();
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Please press the Add new Button to add Item..", ButtonType.OK).show();
        }
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        if(tblItems.getSelectionModel().getSelectedIndex()>=0){
            deleteCustomer();
            LoadAllItems();
        }else{
            new Alert(Alert.AlertType.ERROR, "Please select a Item to delete..", ButtonType.OK).show();
        }
    }
    
    
    private void saveItem(){
        try {
            String code = txtItemCode.getText();
            String description = txtDescription.getText();
            BigDecimal unitPrice =  new BigDecimal(txtUnitPrice.getText());
            int qty = Integer.valueOf(txtQty.getText()) ;
            ItemDTO itemDTO = new ItemDTO(code, description, unitPrice,qty);
            
            Boolean result = itemBO.saveItem(itemDTO);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Item has been saved successfully", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Error on saving Item", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void LoadAllItems(){
        try {
            ArrayList<ItemDTO> allItems = itemBO.getAllItem();
            ArrayList<ItemTM> addItems = new ArrayList<>();
            for (ItemDTO items : allItems) {
                ItemTM item = new ItemTM(items.getCode(), items.getDescription(), items.getUnitPrice(),items.getQtyOnHand());
                addItems.add(item);
            }
            tblItems.setItems(FXCollections.observableArrayList(addItems));
            
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    private void updateItem(){
       
        ItemTM selectedItem =  tblItems.getSelectionModel().getSelectedItem();
        String code = selectedItem.getCode();
        String description = txtDescription.getText();
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText());
        int qty = Integer.parseInt( txtQty.getText()) ;
        ItemDTO item = new ItemDTO(code, description, unitPrice,qty);
        
        try {
            Boolean result = itemBO.updateItem(item);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Item has been Updated successfully..", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Error on update Item...", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void deleteCustomer(){
       ItemTM item = tblItems.getSelectionModel().getSelectedItem();
       String id = item.getCode();
        try {
            boolean result = itemBO.deleteItem(id);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Item has been deleted successfully..", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Error when deleting Item..", ButtonType.OK).show();
            }
                    
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
        
        
    
    
}
