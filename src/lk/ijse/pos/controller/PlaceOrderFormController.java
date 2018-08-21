/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Label;
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
import lk.ijse.pos.business.custom.ItemdetailBO;
import lk.ijse.pos.business.custom.OrdersBO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.ItemdetailDTO;
import lk.ijse.pos.dto.OrdersDTO;
import lk.ijse.pos.main.StartUp;
import lk.ijse.pos.view.util.tblmodel.OrderTM;

/**
 * FXML Controller class
 *
 * @author A C E R
 */
public class PlaceOrderFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtOrderId;
    @FXML
    private JFXDatePicker txtOrderDate;
    @FXML
    private JFXComboBox<String> cmbCustomerId;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXComboBox<String> cmbItemCode;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtStock;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private TableView<OrderTM> tblOrderDetails;
    @FXML
    private Label lblTotal;
    @FXML
    private JFXButton btnPlaceOrder;
    @FXML
    private Label lblDisplayTot;
    
    private ArrayList<OrderTM> orderDetails = new ArrayList<>();
    
    private BigDecimal grandTotal = new BigDecimal(0);
    
    private CustomerBO customerBO;
    private ItemBO itemBO;
    private OrdersBO orderBO;
    private ItemdetailBO itemdetailBO;

    public PlaceOrderFormController() {
            this.customerBO = (CustomerBO)BOFactory.getInstance().getBO(BOFactory.BOType.CustomerBO);
            this.itemBO = (ItemBO)BOFactory.getInstance().getBO(BOFactory.BOType.ItemBO);
            this.orderBO = (OrdersBO)BOFactory.getInstance().getBO(BOFactory.BOType.OrdersBO);
            this.itemdetailBO = (ItemdetailBO)BOFactory.getInstance().getBO(BOFactory.BOType.ItemdetailBO);

    }
    
    
    private void LoadCustomerComboBox(){
        ArrayList<String> customerArray = new ArrayList<>();
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        try {
            customers = customerBO.getAllCustomer();
            
        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (CustomerDTO customer : customers) {
            customerArray.add(customer.getId());
            
        }
        
        cmbCustomerId.setItems(FXCollections.observableArrayList(customerArray));
    }
    
    
    
    private void LoadItemComboBox(){
        ArrayList<String> itemArray = new ArrayList<>();
        ArrayList<ItemDTO> items = new ArrayList<>();
        try {
            items = itemBO.getAllItem();
            
        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (ItemDTO item : items) {
            itemArray.add(item.getCode());
            
        }
        
        cmbItemCode.setItems(FXCollections.observableArrayList(itemArray));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        
        LoadCustomerComboBox();
        LoadItemComboBox();
        
    }    

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
          StartUp.navigateToHome(root, (Stage) this.root.getScene().getWindow());   
    }
    
    
    

    @FXML
    private void customerCodeComboBox(ActionEvent event) {
        try {
            String customerId = cmbCustomerId.getValue();
            CustomerDTO customer = customerBO.findByID(customerId);
            txtCustomerName.setText(customer.getName());
            
        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
  
    @FXML
    private void itemCodeComboBox(ActionEvent event) {
        
        try {
            String itemCode = cmbItemCode.getValue();
            ItemDTO item = itemBO.findByID(itemCode);
            txtDescription.setText(item.getDescription());
            txtStock.setText(item.getQtyOnHand()+"");
            txtUnitPrice.setText(item.getUnitPrice()+"");
            
        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void addItem(ActionEvent event) {
        String itemcode = cmbItemCode.getValue();
        String description = txtDescription.getText();
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText());
        int qty =Integer.parseInt( txtQty.getText() );
        BigDecimal total  = unitPrice.multiply(new BigDecimal(qty));
        
        OrderTM item = new OrderTM(itemcode, description, unitPrice, qty, total);
        orderDetails.add(item);
        tblOrderDetails.setItems(FXCollections.observableArrayList(orderDetails));
        grandTotal = grandTotal.add(item.getTotal());
        lblDisplayTot.setText(grandTotal + "");

    }

    @FXML
    private void removeItem(ActionEvent event) {
        String itemcode = cmbItemCode.getValue();
        if(tblOrderDetails.getSelectionModel().getSelectedIndex()>=0){
            OrderTM order = tblOrderDetails.getSelectionModel().getSelectedItem();
            tblOrderDetails.getItems().removeAll(order);
            orderDetails.remove(order);
            grandTotal = grandTotal.subtract(tblOrderDetails.getSelectionModel().getSelectedItem().getUnitPrice());
            lblDisplayTot.setText(grandTotal + "");
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Please select a item to remove", ButtonType.OK).show();
        }
    }

    @FXML
    private void placeOrderPressed(ActionEvent event) {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            Boolean fresult1 = updateorderTable();
            Boolean fresult2 = updateItemdetailTable();
            if(fresult1 && fresult2){
                DBConnection.getInstance().getConnection().commit();
                new Alert(Alert.AlertType.INFORMATION, "Place Order Added Succesfully..", ButtonType.OK).show();
            }else{
                DBConnection.getInstance().getConnection().rollback();
            }
        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (Exception ex) {
                Logger.getLogger(PlaceOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    
    
    private boolean updateorderTable(){
        
           
        String orderId = txtOrderId.getText();
        LocalDate localDate = txtOrderDate.getValue();
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        } catch (ParseException ex) {
            Logger.getLogger(PlaceOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String customerId = cmbCustomerId.getValue();
        OrdersDTO ordersDTO = new OrdersDTO(orderId, date, customerId);
        Boolean result = false;       
        try {
            result = orderBO.saveOrders(ordersDTO);
        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;   
        
    }
    
    private boolean updateItemdetailTable(){
        String orderId = txtOrderId.getText();
        ArrayList<ItemdetailDTO> itemdetails = new ArrayList<>();
        Boolean result = true;
        for (OrderTM orderDetail : orderDetails) {
            ItemdetailDTO itemdetailDTO = new ItemdetailDTO(orderId, orderDetail.getCode(),orderDetail.getQty(),orderDetail.getUnitPrice());
            try {
                itemdetailBO.saveItemdetail(itemdetailDTO);
            } catch (Exception ex) {
                result = false;
                Logger.getLogger(PlaceOrderFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    }
    
    
    
    
    
    
    
    
    
    

