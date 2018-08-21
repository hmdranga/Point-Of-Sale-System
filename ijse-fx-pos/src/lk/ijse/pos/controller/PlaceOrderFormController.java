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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.main.StartUp;

/**
 *
 * @author ranjith-suranga
 */
public class PlaceOrderFormController implements Initializable{

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
    private TableView<?> tblOrderDetails;
    @FXML
    private Label lblTotal;
    @FXML
    private JFXButton btnPlaceOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        StartUp.navigateToHome(root, (Stage) root.getScene().getWindow());        
    }
    
}
