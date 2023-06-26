package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.custom.CustomerDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.entity.Brand;
import lk.ijse.gdse.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {

    private final Connection connection;

    public CustomerDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Customer save(Customer customer) throws ConstraintViolationException {
        try {
            if(DBUtil.executeUpdate("INSERT INTO customer (cid, name, address,tel_num) VALUES (?,?,?,?)",
                    customer.getId(),customer.getName(),customer.getAddress(),customer.getTelephone())){
                return customer;
            }
            throw new SQLException("Failed to save the customer");
        }catch (SQLException e){
            throw new ConstraintViolationException(e);
        }

    }

    @Override
    public Customer update(Customer customer) throws ConstraintViolationException {
        try {
            if(DBUtil.executeUpdate("UPDATE Customer SET name=? ,address=? ,tel_num=? WHERE cid=?",customer.getName(),customer.getAddress(),customer.getTelephone(),customer.getId())){
                return customer;
            }
            throw new SQLException("Failed to update the Customer");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public void deleteByPk(String id) throws ConstraintViolationException {
        try {
            if(!DBUtil.executeUpdate("DELETE FROM Customer WHERE cid=?",id)) throw new SQLException("Failed to delete the customer");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Customer> findAll() {
        try{
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Customer");
            return getCustomerList(rst);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load the customer");
        }
    }

    @Override
    public Optional<Customer> findByPk(String id) {
        try{
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Customer WHERE cid=?", id);
            if(rst.next()){
                return Optional.of(new Customer(rst.getString("cid"), rst.getString("name"), rst.getString("address"), rst.getString("tel_num")));

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the customer details");
        }
    }

    @Override
    public boolean existByPk(String id) {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Customer WHERE cid=?", id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(cid) AS count FROM Customer");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> searchByText(String text) {
        try{
            text="%"+text+"%";
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM Customer WHERE cid LIKE ? OR name LIKE ? OR address LIKE ? ", text, text, text);
            return getCustomerList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Customer> getCustomerList(ResultSet rst)  {
        try {
            List<Customer> bookList= new ArrayList<>();
            while (rst.next()){
                Customer customer = new Customer(rst.getString("cid"), rst.getString("name"), rst.getString("address"), rst.getString("tel_num"));
                bookList.add(customer);
            }
            return bookList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<String> loadCustomerIDCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cid FROM customer";
        ResultSet result = DBUtil.executeQuery(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }
    public static ArrayList<Customer> getAllCustomer() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getDbConnection().getConnection()
                .createStatement().executeQuery("SELECT * FROM Customer");

        ArrayList<Customer>customerList=new ArrayList<>();

        while(rst.next()){
            Customer customer = new Customer(rst.getString("cid"), rst.getString("name"),
                    rst.getString("address"),rst.getString("tel_num"));
            customerList.add(customer);
        }
        return customerList;
    }
    public static Customer search(String code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE cid = ?";
        ResultSet result = DBUtil.executeQuery(sql,code);

        if (result.next()) {
            return new Customer( result.getString(2));



        }
        return null;
    }

}
