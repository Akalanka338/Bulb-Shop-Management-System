package lk.ijse.gdse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.dao.custom.Impl.CustomerDAOImpl;
import lk.ijse.gdse.dao.custom.Impl.ItemDAOImpl;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.CartDetailDTO;
import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.OrderDetail;
import lk.ijse.gdse.entity.*;
import lk.ijse.gdse.service.custom.CustomerService;
import lk.ijse.gdse.service.custom.Impl.OrderDetailServiceImpl;
import lk.ijse.gdse.service.custom.OrderDetailService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.tm.CustomerTM;
import lk.ijse.gdse.tm.OrderDetailTM;
import lk.ijse.gdse.tm.PlaceOrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class OrderManageFormController {

    public AnchorPane OrderDetailsFormContext;
    public AnchorPane AddOrderFormCOntext;
    public TextField txtQty;
    public TableView tblOrderCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colAction;
    public Label lblOrderId;
    public Label lblOrderDate;
    public Label lblDescription;
    public JFXComboBox cmbItemCode;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public TextField txtdescription;
    public TextField txtPrice;
    public TextField txtQtyOnhAND;
    public JFXComboBox cmdCustomer;
    public TextField txtCustomerName;
    public Label lblTotal;
    private ObservableList<PlaceOrder> obList = FXCollections.observableArrayList();

    public OrderDetailService orderDetailService;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadOrderDate();
        CustomerModel();
        loadItemCodes();
        setCellValueFactory();
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue) ->{
            if(newValue!=null){
                try {
                    searchItemDetails();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        cmdCustomer.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue) ->{
            if(newValue!=null){
                try {
                    searchCustomerDetails();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        qty();
        String code = String.valueOf(cmbItemCode.getValue());
        int qty = Integer.parseInt(txtQty.getText());
        String desc = txtdescription.getText();
        double unitPrice = Double.parseDouble(txtPrice.getText());
        double total = unitPrice * qty;
        Button btnDelete = new Button("Delete");

        txtQty.setText("");

        if (!obList.isEmpty()) {
            L1:

            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblOrderCart.refresh();
                    return;
                }
            }

        }
        lblTotal.setText(String.valueOf(total));

        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                PlaceOrder placeOrder = (PlaceOrder) tblOrderCart.getSelectionModel().getSelectedItem();


                tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new PlaceOrder(code, desc, qty, unitPrice, total, btnDelete));
        tblOrderCart.setItems(obList);

    }

    public void CustomerOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmdCustomer.getValue());
        try {
            Customer customer = CustomerDAOImpl.search(code);
            fillItemFields(customer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) {


        String orderId = lblOrderId.getText();

        String customerId = String.valueOf(cmdCustomer.getValue().toString());

        ArrayList<CartDetail> cartDetails = new ArrayList<>();


        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {


            PlaceOrder tm = obList.get(i);

            cartDetails.add(new CartDetail(orderId, tm.getCode(), tm.getQty(), tm.getDescription(), tm.getUnitPrice()));
            new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();

        }

        PlaceOrder placeOrder = new PlaceOrder(customerId, orderId, cartDetails);
        System.out.println(placeOrder);
      /*  try {
            boolean isPlaced = placeOrder(placeOrder);
            if (isPlaced) {
                obList.clear();
                //loadNextOrderId();


                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/


    }




    public void InvoiceOnAction(ActionEvent actionEvent) throws JRException {
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/gdse/reports/Invoice2.jrxml");
        JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null,
                DBConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }
    private void loadOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

   /* private void loadNextOrderId() {
        try {
            String orderId = OrderModel.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }


    private void CustomerModel() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = CustomerDAOImpl.loadCustomerIDCodes();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmdCustomer.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillItemFields(Customer customer) {
        txtCustomerName.setText(customer.getName());

    }
    private void loadItemCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = ItemDAOImpl.loadItemCodes();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbItemCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchCustomerDetails() throws SQLException, ClassNotFoundException {
        for (Customer c1: CustomerDAOImpl.getAllCustomer()
        ) {
            if (c1.getId().equals(cmdCustomer.getValue())){
                txtCustomerName.setText(c1.getName());

            }
        }

    }
    private void searchItemDetails() throws SQLException, ClassNotFoundException {
        for (Item i1: ItemDAOImpl.getAllItem()
        ) {
            if (i1.getCode().equals(cmbItemCode.getValue())){
                txtdescription.setText(i1.getName());
                txtPrice.setText(String.valueOf(i1.getSelling_price()));
                txtQtyOnhAND.setText(String.valueOf(i1.getQty()));
            }
        }

    }
    private void qty(){
        int pid = Integer.parseInt(txtQtyOnhAND.getText());
        int sid = Integer.parseInt(txtQty.getText());
        int tot = pid-sid;
        txtQtyOnhAND.setText(String.valueOf(tot));


    }

}
