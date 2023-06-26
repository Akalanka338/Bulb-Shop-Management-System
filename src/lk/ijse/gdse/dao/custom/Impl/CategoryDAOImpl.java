package lk.ijse.gdse.dao.custom.Impl;

import lk.ijse.gdse.dao.custom.CategoryDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.dao.util.DBUtil;
import lk.ijse.gdse.entity.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAOImpl implements CategoryDAO {

    private final Connection connection;

    public CategoryDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Category save(Category entity) throws ConstraintViolationException {
        return null;
    }

    @Override
    public Category update(Category entity) throws ConstraintViolationException {
        return null;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {

    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findByPk(String pk) {
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
    public List<Category> searchByText(String text) {
        return null;
    }


    public static ArrayList<String> loadCategoryName() throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM catogory";
        ResultSet result = DBUtil.executeQuery(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }
}
