package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.custom.BrandDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Brand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrandDAOImpl implements BrandDAO {

    private final Connection connection;

    public BrandDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Brand save(Brand entity) throws ConstraintViolationException {
        return null;
    }

    @Override
    public Brand update(Brand entity) throws ConstraintViolationException {
        return null;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {

    }

    @Override
    public List<Brand> findAll() {
        return null;
    }

    @Override
    public Optional<Brand> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Brand> searchByText(String text) {
        return null;
    }

    public static ArrayList<String> loadBrandName() throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM brand";
        ResultSet result = DBUtil.executeQuery(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

}
