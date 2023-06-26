package lk.ijse.gdse.service.custom;

import lk.ijse.gdse.dto.CustomerDTO;
import lk.ijse.gdse.dto.ItemDTO;
import lk.ijse.gdse.service.SuperService;
import lk.ijse.gdse.service.exception.DuplicateException;
import lk.ijse.gdse.service.exception.InUseException;
import lk.ijse.gdse.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface ItemService extends SuperService {
    public ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException;

    public ItemDTO updateItem(ItemDTO itemDTO)  throws NotFoundException;

    public void deleteItem(String id) throws NotFoundException, InUseException, SQLException,ClassNotFoundException;

    public List<ItemDTO> findAllItem();

    public ItemDTO findItemById(String id) throws NotFoundException;

    public List<ItemDTO> searchItemByText(String text) ;
}
