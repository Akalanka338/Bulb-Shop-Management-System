package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.dao.custom.Impl.BrandDAOImpl;
import lk.ijse.gdse.dao.custom.Impl.CategoryDAOImpl;
import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.ItemDTO;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.ServiceTypes;
import lk.ijse.gdse.service.custom.CustomerService;
import lk.ijse.gdse.service.custom.ItemService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.tm.CustomerTM;
import lk.ijse.gdse.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ManageStockController {
    public AnchorPane ManageItemFomContext;
    public TextField txtItemCode;
    public ComboBox cmbBrand;
    public TextField txtPurchasePrice;
    public TextField txtSellingPrice;
    public TextField txtModelNumber;
    public TextField txtQauntitiy;
    public TextField txtName;
    public ComboBox cmbCatogory;
    public TableView <ItemTM>tblItem;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colPprice;
    public TableColumn colSprice;
    public TableColumn colProfit;
    public TableColumn colQty;
    public TableColumn colModel;
    public TableColumn colCategory;
    public Label lblProfit;

    public ItemService itemService;
    public Button btnUpdate;
    public TextField txtSearch;


    public void initialize() throws SQLException, ClassNotFoundException {
        loadBrandName();
        CategoryModel();

        this.itemService= ServiceFactory.getInstance().getService(ServiceTypes.ITEM);

        btnUpdate.setDisable(true);
        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ID"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Pprice"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Sprice"));
        tblItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("profit"));
        tblItem.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblItem.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblItem.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("category"));


        List<ItemTM> itemTMList = itemService.findAllItem().stream().map(i -> new ItemTM(i.getCode(),i.getName(), i.getBrand(), i.getCatogory(),i.getPurchase_price(),i.getSelling_price(),i.getProfit(),i.getModel_number(),i.getQty())).collect(Collectors.toList());

        tblItem.setItems(FXCollections.observableArrayList(itemTMList));

        txtSearch.textProperty().addListener((observableValue, pre, curr) ->{
            if (!Objects.equals(pre, curr)){
                tblItem.getItems().clear();
                List<ItemTM> itemList = itemService.searchItemByText(curr).stream().map(i ->new ItemTM(i.getCode(),i.getName(), i.getBrand(), i.getCatogory(),i.getPurchase_price(),i.getSelling_price(),i.getProfit(),i.getModel_number(),i.getQty())).collect(Collectors.toList());
                tblItem.setItems(FXCollections.observableArrayList(itemList));
            }

        } );

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, pre, curr) -> {
            if (curr!=pre || curr!=null){
                btnUpdate.setDisable(false);
            }

        });
    }



    public void AddOnAction(ActionEvent actionEvent) {
        profit();
        ItemDTO item =new ItemDTO(txtItemCode.getText(),txtName.getText(),(String) cmbBrand.getValue(),(String) cmbCatogory.getValue(),Double.parseDouble(txtPurchasePrice.getText()),Double.parseDouble(txtSellingPrice.getText()),Double.parseDouble(lblProfit.getText()),txtModelNumber.getText(),Integer.parseInt(txtQauntitiy.getText()));

        try {
            if(itemService.saveItem(item)==null) {
                new Alert(Alert.AlertType.ERROR,"Failed to Save the item !").show();
                return;
            }
            new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added !").show();
            tblItem.getItems().add(new ItemTM(item.getCode(),item.getName(),item.getBrand(),item.getCatogory(), item.getPurchase_price(), item.getSelling_price(), item.getProfit(), item.getModel_number(), item.getQty()));
            txtItemCode.clear();
            txtName.clear();
            txtSellingPrice.clear();
            txtSellingPrice.clear();
            txtModelNumber.clear();
            txtQauntitiy.clear();
            txtItemCode.requestFocus();
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,"ItemDTO Already Exists").show();
            txtItemCode.selectAll();
            txtItemCode.requestFocus();
        }


    }

    private void loadBrandName() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = BrandDAOImpl.loadBrandName();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbBrand.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void CategoryModel() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = CategoryDAOImpl.loadCategoryName();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbCatogory.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void profit(){
        Double pid = Double.parseDouble(txtPurchasePrice.getText());
        Double sid = Double.parseDouble(txtSellingPrice.getText());
        Double profit = sid-pid;
        lblProfit.setText(String.valueOf(profit));


    }
}
