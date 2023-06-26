package lk.ijse.gdse.service.custom.Impl;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.DaoTypes;
import lk.ijse.gdse.dao.custom.CustomerDAO;
import lk.ijse.gdse.dao.custom.ItemDAO;
import lk.ijse.gdse.dao.exception.ConstraintViolationException;
import lk.ijse.gdse.db.DBConnection;
import lk.ijse.gdse.dto.ItemDTO;
import lk.ijse.gdse.entity.Customer;
import lk.ijse.gdse.entity.Item;
import lk.ijse.gdse.service.custom.ItemService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.service.exception.InUseException;
import lk.ijse.gdse.service.exception.NotFoundException;
import lk.ijse.gdse.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;
    private final Convertor convertor;
    private final Connection connection;

    public ItemServiceImpl() {
        connection= DBConnection.getDbConnection().getConnection();
        itemDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.ITEM );
        convertor=new Convertor();
    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException {
        if (itemDAO.existByPk(itemDTO.getCode())) throw new DuplicateException("Item already saved");

        itemDAO.save(convertor.toItem(itemDTO));

        return itemDTO;
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) throws NotFoundException {
        if (!itemDAO.existByPk(itemDTO.getCode())) throw new NotFoundException("Item not found");

        itemDAO.update(convertor.toItem(itemDTO));

        return itemDTO;
    }

    @Override
    public void deleteItem(String id) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {
        if (!itemDAO.existByPk(id)) throw new NotFoundException("item not found");

        try {
            itemDAO.existByPk(id);
        }catch (ConstraintViolationException e){
            throw new InUseException("Item already in used");
        }
    }

    @Override
    public List<ItemDTO> findAllItem() {
        return itemDAO.findAll().stream().map(item -> convertor.fromItem(item)).collect(Collectors.toList());
    }

    @Override
    public ItemDTO findItemById(String id) throws NotFoundException {
        Optional<Item> optionalItem = itemDAO.findByPk(id);
        if (!optionalItem.isPresent()) throw new NotFoundException("Item not found");

        return convertor.fromItem(optionalItem.get());
    }

    @Override
    public List<ItemDTO> searchItemByText(String text) {
        return itemDAO.searchByText(text).stream().map(item -> convertor.fromItem(item)).collect(Collectors.toList());
    }
}
