package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.custom.ItemDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.entity.CartDetail;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAOImpl implements ItemDAO {


    private final Connection connection;

    public ItemDAOImpl(Connection connection) {
        this.connection = connection;

    }

    @Override
    public Item save(Item item) throws ConstraintViolationException {
        try {
            if(DBUtil.executeUpdate("INSERT INTO Item (item_code, name, brand,catogory,pprice,sprice,profit,mnumber,qty) VALUES (?,?,?,?,?,?,?,?,?)",
                    item.getCode(),item.getName(),item.getBrand(),item.getCatogory(),item.getPurchase_price(),item.getSelling_price(),item.getProfit(),item.getModel_number(),item.getQty())){

                return item;
            }
            throw new SQLException("Failed to save the item");
        }catch (SQLException e){
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public Item update(Item item) throws ConstraintViolationException {
        try {
            if(DBUtil.executeUpdate("UPDATE Item SET name=? ,brand=? ,catogory=? , pprice=? ,sprice=? ,profit=? ,mnumber=? ,qty=? WHERE item_code=?",item.getCode(),item.getName(),item.getBrand(),item.getCatogory(),item.getPurchase_price(),item.getSelling_price(),item.getProfit(),item.getModel_number(),item.getQty())){
                return item;
            }
            throw new SQLException("Failed to update the Item");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public void deleteByPk(String id) throws ConstraintViolationException {
        try {
            if(!DBUtil.executeUpdate("DELETE FROM Item WHERE  item_code=?",id)) throw new SQLException("Failed to delete the item");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Item> findAll() {
        try{
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Item");
            return getItemList(rst);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load the item");
        }
    }

    @Override
    public Optional<Item> findByPk(String id) {
        try{
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Item WHERE item_code=?", id);
            if(rst.next()){
                return Optional.of(new Item(rst.getString(" item_code"), rst.getString("name"), rst.getString("brand"), rst.getString("catogory"),rst.getDouble("pprice"),rst.getDouble("sprice"),rst.getDouble("profit"),rst.getString("mnumber"),rst.getInt("qty")));

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the item details");
        }
    }

    @Override
    public boolean existByPk(String id) {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Item WHERE  item_code=?", id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(item_code) AS count FROM Item");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> searchByText(String text) {
        try{
            text="%"+text+"%";
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Customer WHERE item_code LIKE ? OR name LIKE ? OR brand LIKE ? ", text, text, text);
            return getItemList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Item> getItemList(ResultSet rst)  {
        try {
            List<Item> itemList= new ArrayList<>();
            while (rst.next()){
                Item item = new Item(rst.getString("item_code"), rst.getString("name"), rst.getString("brand"), rst.getString("catogory"),rst.getDouble("pprice"),rst.getDouble("sprice"),rst.getDouble("profit"),rst.getString("mnumber"),rst.getInt("qty"));

                itemList.add(item);
            }
            return itemList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT item_code FROM item";
        ResultSet result = DBUtil.executeQuery(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    /*private static boolean updateQty(ArrayList<CartDetail> item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET qty = qty - ? WHERE item_code = ?";
        return DBUtil.executeUpdate(sql, item.getQty(), item.getCode());
    }*/

    public Optional<Item> getItem(String id) {
        try{
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Item WHERE item_code=?", id);
            if(rst.next()){
                return Optional.of(new Item(rst.getString(" item_code"), rst.getString("name"), rst.getString("brand"), rst.getString("catogory"),rst.getDouble("pprice"),rst.getDouble("sprice"),rst.getDouble("profit"),rst.getString("mnumber"),rst.getInt("qty")));

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the item details");
        }
    }
    public static ArrayList<Item> getAllItem() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getDbConnection().getConnection()
                .createStatement().executeQuery("SELECT * FROM item");

        ArrayList<Item>itemList=new ArrayList<>();

        while(rst.next()){
            Item item = new Item(rst.getString("item_code"),
                    rst.getString("name"),
                    rst.getString("brand"),
                    rst.getString("catogory"),
                    rst.getDouble("pprice"),
                    rst.getDouble("sprice"),
                    rst.getDouble("profit"),
                    rst.getString("mnumber"),
                    rst.getInt("qty"));
            itemList.add(item);
        }
        return itemList;
    }

}
